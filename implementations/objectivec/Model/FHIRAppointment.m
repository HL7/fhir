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
 */
#import "FHIRAppointment.h"

#import "FHIRIdentifier.h"
#import "FHIRInteger.h"
#import "FHIRCode.h"
#import "FHIRString.h"
#import "FHIRInstant.h"
#import "FHIRSchedule.h"
#import "FHIRResourceReference.h"
#import "FHIRAppointmentParticipantComponent.h"
#import "FHIRDateTime.h"

#import "FHIRErrorList.h"

@implementation FHIRAppointment

- (NSNumber *)priority
{
    if(self.priorityElement)
    {
        return [self.priorityElement value];
    }
    return nil;
}

- (void )setPriority:(NSNumber *)priority
{
    if(priority)
    {
        [self setPriorityElement:[[FHIRInteger alloc] initWithValue:priority]];
    }
    else
    {
        [self setPriorityElement:nil];
    }
}


- (NSString *)status
{
    if(self.statusElement)
    {
        return [self.statusElement value];
    }
    return nil;
}

- (void )setStatus:(NSString *)status
{
    if(status)
    {
        [self setStatusElement:[[FHIRCode alloc] initWithValue:status]];
    }
    else
    {
        [self setStatusElement:nil];
    }
}


- (NSString *)description
{
    if(self.descriptionElement)
    {
        return [self.descriptionElement value];
    }
    return nil;
}

- (void )setDescription:(NSString *)description
{
    if(description)
    {
        [self setDescriptionElement:[[FHIRString alloc] initWithValue:description]];
    }
    else
    {
        [self setDescriptionElement:nil];
    }
}


- (NSDate *)start
{
    if(self.startElement)
    {
        return [self.startElement value];
    }
    return nil;
}

- (void )setStart:(NSDate *)start
{
    if(start)
    {
        [self setStartElement:[[FHIRInstant alloc] initWithValue:start]];
    }
    else
    {
        [self setStartElement:nil];
    }
}


- (NSDate *)end
{
    if(self.endElement)
    {
        return [self.endElement value];
    }
    return nil;
}

- (void )setEnd:(NSDate *)end
{
    if(end)
    {
        [self setEndElement:[[FHIRInstant alloc] initWithValue:end]];
    }
    else
    {
        [self setEndElement:nil];
    }
}


- (NSString *)timezone
{
    if(self.timezoneElement)
    {
        return [self.timezoneElement value];
    }
    return nil;
}

- (void )setTimezone:(NSString *)timezone
{
    if(timezone)
    {
        [self setTimezoneElement:[[FHIRString alloc] initWithValue:timezone]];
    }
    else
    {
        [self setTimezoneElement:nil];
    }
}


- (NSString *)comment
{
    if(self.commentElement)
    {
        return [self.commentElement value];
    }
    return nil;
}

- (void )setComment:(NSString *)comment
{
    if(comment)
    {
        [self setCommentElement:[[FHIRString alloc] initWithValue:comment]];
    }
    else
    {
        [self setCommentElement:nil];
    }
}


- (NSString *)recordedDate
{
    if(self.recordedDateElement)
    {
        return [self.recordedDateElement value];
    }
    return nil;
}

- (void )setRecordedDate:(NSString *)recordedDate
{
    if(recordedDate)
    {
        [self setRecordedDateElement:[[FHIRDateTime alloc] initWithValue:recordedDate]];
    }
    else
    {
        [self setRecordedDateElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identifier != nil )
        for(FHIRIdentifier *elem in self.identifier)
            [result addValidationRange:[elem validate]];
    if(self.priorityElement != nil )
        [result addValidationRange:[self.priorityElement validate]];
    if(self.statusElement != nil )
        [result addValidationRange:[self.statusElement validate]];
    if(self.descriptionElement != nil )
        [result addValidationRange:[self.descriptionElement validate]];
    if(self.startElement != nil )
        [result addValidationRange:[self.startElement validate]];
    if(self.endElement != nil )
        [result addValidationRange:[self.endElement validate]];
    if(self.schedule != nil )
        [result addValidationRange:[self.schedule validate]];
    if(self.timezoneElement != nil )
        [result addValidationRange:[self.timezoneElement validate]];
    if(self.slot != nil )
        for(FHIRResourceReference *elem in self.slot)
            [result addValidationRange:[elem validate]];
    if(self.location != nil )
        [result addValidationRange:[self.location validate]];
    if(self.commentElement != nil )
        [result addValidationRange:[self.commentElement validate]];
    if(self.order != nil )
        [result addValidationRange:[self.order validate]];
    if(self.participant != nil )
        for(FHIRAppointmentParticipantComponent *elem in self.participant)
            [result addValidationRange:[elem validate]];
    if(self.recorder != nil )
        [result addValidationRange:[self.recorder validate]];
    if(self.recordedDateElement != nil )
        [result addValidationRange:[self.recordedDateElement validate]];
    
    return result;
}

@end
