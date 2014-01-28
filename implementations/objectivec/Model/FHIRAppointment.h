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
 * (informative) A scheduled appointment for a patient and/or practitioner(s) where a service may take place
 *
 * [FhirResource("Appointment")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRInteger;
@class FHIRCode;
@class FHIRString;
@class FHIRInstant;
@class FHIRSchedule;
@class FHIRResourceReference;
@class FHIRAppointmentParticipantComponent;
@class FHIRDateTime;

@interface FHIRAppointment : FHIRResource

/*
 * The free/busy status of an appointment
 */
typedef enum 
{
    kAppointmentStatusBusy, // The participant(s) will be unavailable during this appointment.
    kAppointmentStatusFree, // The participant(s) will still be available during this appointment.
    kAppointmentStatusTentative, // This appointment has not been confirmed, and may become available.
    kAppointmentStatusOutofoffice, // The participant(s) will not be at the usual location.
} kAppointmentStatus;

/*
 * Is the Participant required to attend the appointment
 */
typedef enum 
{
    kParticipantRequiredRequired, // The participant is required to attend the appointment.
    kParticipantRequiredOptional, // The participant may optionally attend the appointment.
    kParticipantRequiredInformationOnly, // The participant is not required to attend the appointment (appointment is about them, not for them).
} kParticipantRequired;

/*
 * The Participation status of an appointment
 */
typedef enum 
{
    kParticipationStatusAccepted, // The participant has accepted the appointment.
    kParticipationStatusDeclined, // The participant has declined the appointment.
    kParticipationStatusTentative, // The participant has tentative the appointment.
    kParticipationStatusInProcess, // The participant has in-process the appointment.
    kParticipationStatusCompleted, // The participant has completed the appointment.
    kParticipationStatusNeedsAction, // The participant has needs-action the appointment.
} kParticipationStatus;

/*
 * External Ids for this item
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * The priority of the appointment. Can be used to make informed decisions if needing to re-prioritize appointments. (The iCal Standard specifies 0 as undefined, 1 as highest, 9 as lowest priority) (Need to change back to CodeableConcept)
 */
@property (nonatomic, strong) FHIRInteger *priorityElement;

@property (nonatomic, strong) NSNumber *priority;

/*
 * The overall status of the Appointment
 */
@property (nonatomic, strong) FHIRCode *statusElement;

@property (nonatomic, strong) NSString *status;

/*
 * The brief description of the appointment as would be shown on a subject line in a meeting request, or appointment list. Detailed or expanded information should be put in the comment field
 */
@property (nonatomic, strong) FHIRString *descriptionElement;

@property (nonatomic, strong) NSString *description;

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
 * The slot that this appointment is filling. If provided then the schedule will not be provided as slots are not recursive, and the start/end values MUST be the same as from the slot
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *slot;

/*
 * The primary location that this appointment is to take place
 */
@property (nonatomic, strong) FHIRResourceReference *location;

/*
 * Additional comments about the appointment
 */
@property (nonatomic, strong) FHIRString *commentElement;

@property (nonatomic, strong) NSString *comment;

/*
 * An Order that lead to the creation of this appointment
 */
@property (nonatomic, strong) FHIRResourceReference *order;

/*
 * List of participants involved in the appointment
 */
@property (nonatomic, strong) NSArray/*<AppointmentParticipantComponent>*/ *participant;

/*
 * Who recorded the appointment
 */
@property (nonatomic, strong) FHIRResourceReference *recorder;

/*
 * Date when the sensitivity was recorded
 */
@property (nonatomic, strong) FHIRDateTime *recordedDateElement;

@property (nonatomic, strong) NSString *recordedDate;

- (FHIRErrorList *)validate;

@end
