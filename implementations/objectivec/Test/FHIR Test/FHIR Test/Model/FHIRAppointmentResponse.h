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
 * (informative) A response to a scheduled appointment for a patient and/or practitioner(s)
 *
 * [FhirResource("AppointmentResponse")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRResourceReference;
@class FHIRCodeableConcept;
@class FHIRCode;
@class FHIRString;
@class FHIRInstant;
@class FHIRSchedule;
@class FHIRDateTime;

@interface FHIRAppointmentResponse : FHIRResource

/*
 * The Participation status of an appointment
 */
typedef enum 
{
    kParticipantStatusAccepted, // The participant has accepted the appointment.
    kParticipantStatusDeclined, // The participant has declined the appointment.
    kParticipantStatusTentative, // The participant has tentative the appointment.
    kParticipantStatusInProcess, // The participant has in-process the appointment.
    kParticipantStatusCompleted, // The participant has completed the appointment.
    kParticipantStatusNeedsAction, // The participant has needs-action the appointment.
} kParticipantStatus;

/*
 * External Ids for this item
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * Parent appointment that this response is replying to
 */
@property (nonatomic, strong) FHIRResourceReference *appointment;

/*
 * Role of participant in the appointment
 */
@property (nonatomic, strong) NSArray/*<CodeableConcept>*/ *participantType;

/*
 * A Person of device that is participating in the appointment
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *individual;

/*
 * accepted | declined | tentative | in-process | completed | needs-action
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *participantStatusElement;

@property (nonatomic) kParticipantStatus participantStatus;

/*
 * Additional comments about the appointment
 */
@property (nonatomic, strong) FHIRString *commentElement;

@property (nonatomic, strong) NSString *comment;

/*
 * Date/Time that the appointment is to take place
 */
@property (nonatomic, strong) FHIRInstant *startElement;

@property (nonatomic, strong) NSDate *start;

/*
 * Date/Time that the appointment is to conclude
 */
@property (nonatomic, strong) FHIRInstant *endElement;

@property (nonatomic, strong) NSDate *end;

/*
 * The recurrence schedule for the appointment. The end date in the schedule marks the end of the recurrence(s), not the end of an individual appointment
 */
@property (nonatomic, strong) FHIRSchedule *schedule;

/*
 * The timezone that the times are to be converted to. Required for recurring appointments to remain accurate where the schedule makes the appointment cross a daylight saving boundry
 */
@property (nonatomic, strong) FHIRString *timezoneElement;

@property (nonatomic, strong) NSString *timezone;

/*
 * Who recorded the appointment response
 */
@property (nonatomic, strong) FHIRResourceReference *recorder;

/*
 * Date when the response was recorded or last updated
 */
@property (nonatomic, strong) FHIRDateTime *recordedDateElement;

@property (nonatomic, strong) NSString *recordedDate;

- (FHIRErrorList *)validate;

@end
