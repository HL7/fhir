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
 */
#import "FHIRAppointmentResponse.h"

#import "FHIRIdentifier.h"
#import "FHIRResourceReference.h"
#import "FHIRCodeableConcept.h"
#import "FHIRCode.h"
#import "FHIRString.h"
#import "FHIRInstant.h"
#import "FHIRSchedule.h"
#import "FHIRDateTime.h"

#import "FHIRErrorList.h"

@implementation FHIRAppointmentResponse

- (kParticipantStatus )participantStatus
{
    return [FHIREnumHelper parseString:[self.participantStatusElement value] enumType:kEnumTypeParticipantStatus];
}

- (void )setParticipantStatus:(kParticipantStatus )participantStatus
{
    [self setParticipantStatusElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:participantStatus enumType:kEnumTypeParticipantStatus]]];
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
    if(self.appointment != nil )
        [result addValidationRange:[self.appointment validate]];
    if(self.participantType != nil )
        for(FHIRCodeableConcept *elem in self.participantType)
            [result addValidationRange:[elem validate]];
    if(self.individual != nil )
        for(FHIRResourceReference *elem in self.individual)
            [result addValidationRange:[elem validate]];
    if(self.participantStatusElement != nil )
        [result addValidationRange:[self.participantStatusElement validate]];
    if(self.commentElement != nil )
        [result addValidationRange:[self.commentElement validate]];
    if(self.startElement != nil )
        [result addValidationRange:[self.startElement validate]];
    if(self.endElement != nil )
        [result addValidationRange:[self.endElement validate]];
    if(self.schedule != nil )
        [result addValidationRange:[self.schedule validate]];
    if(self.timezoneElement != nil )
        [result addValidationRange:[self.timezoneElement validate]];
    if(self.recorder != nil )
        [result addValidationRange:[self.recorder validate]];
    if(self.recordedDateElement != nil )
        [result addValidationRange:[self.recordedDateElement validate]];
    
    return result;
}

@end
