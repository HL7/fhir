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
 * Healthcare plan for patient
 */
#import "FHIRCarePlan.h"

#import "FHIRIdentifier.h"
#import "FHIRResourceReference.h"
#import "FHIRCode.h"
#import "FHIRPeriod.h"
#import "FHIRDateTime.h"
#import "FHIRCarePlanParticipantComponent.h"
#import "FHIRCarePlanGoalComponent.h"
#import "FHIRCarePlanActivityComponent.h"
#import "FHIRString.h"

#import "FHIRErrorList.h"

@implementation FHIRCarePlan

- (kCarePlanStatus )status
{
    return [FHIREnumHelper parseString:[self.statusElement value] enumType:kEnumTypeCarePlanStatus];
}

- (void )setStatus:(kCarePlanStatus )status
{
    [self setStatusElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:status enumType:kEnumTypeCarePlanStatus]]];
}


- (NSString *)modified
{
    if(self.modifiedElement)
    {
        return [self.modifiedElement value];
    }
    return nil;
}

- (void )setModified:(NSString *)modified
{
    if(modified)
    {
        [self setModifiedElement:[[FHIRDateTime alloc] initWithValue:modified]];
    }
    else
    {
        [self setModifiedElement:nil];
    }
}


- (NSString *)notes
{
    if(self.notesElement)
    {
        return [self.notesElement value];
    }
    return nil;
}

- (void )setNotes:(NSString *)notes
{
    if(notes)
    {
        [self setNotesElement:[[FHIRString alloc] initWithValue:notes]];
    }
    else
    {
        [self setNotesElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identifier != nil )
        for(FHIRIdentifier *elem in self.identifier)
            [result addValidationRange:[elem validate]];
    if(self.patient != nil )
        [result addValidationRange:[self.patient validate]];
    if(self.statusElement != nil )
        [result addValidationRange:[self.statusElement validate]];
    if(self.period != nil )
        [result addValidationRange:[self.period validate]];
    if(self.modifiedElement != nil )
        [result addValidationRange:[self.modifiedElement validate]];
    if(self.concern != nil )
        for(FHIRResourceReference *elem in self.concern)
            [result addValidationRange:[elem validate]];
    if(self.participant != nil )
        for(FHIRCarePlanParticipantComponent *elem in self.participant)
            [result addValidationRange:[elem validate]];
    if(self.goal != nil )
        for(FHIRCarePlanGoalComponent *elem in self.goal)
            [result addValidationRange:[elem validate]];
    if(self.activity != nil )
        for(FHIRCarePlanActivityComponent *elem in self.activity)
            [result addValidationRange:[elem validate]];
    if(self.notesElement != nil )
        [result addValidationRange:[self.notesElement validate]];
    
    return result;
}

@end
