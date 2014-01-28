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
 * An action that is performed on a patient
 */
#import "FHIRProcedure.h"

#import "FHIRIdentifier.h"
#import "FHIRResourceReference.h"
#import "FHIRCodeableConcept.h"
#import "FHIRProcedurePerformerComponent.h"
#import "FHIRPeriod.h"
#import "FHIRString.h"
#import "FHIRProcedureRelatedItemComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRProcedure

- (NSString *)outcome
{
    if(self.outcomeElement)
    {
        return [self.outcomeElement value];
    }
    return nil;
}

- (void )setOutcome:(NSString *)outcome
{
    if(outcome)
    {
        [self setOutcomeElement:[[FHIRString alloc] initWithValue:outcome]];
    }
    else
    {
        [self setOutcomeElement:nil];
    }
}


- (NSString *)followUp
{
    if(self.followUpElement)
    {
        return [self.followUpElement value];
    }
    return nil;
}

- (void )setFollowUp:(NSString *)followUp
{
    if(followUp)
    {
        [self setFollowUpElement:[[FHIRString alloc] initWithValue:followUp]];
    }
    else
    {
        [self setFollowUpElement:nil];
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
    if(self.subject != nil )
        [result addValidationRange:[self.subject validate]];
    if(self.type != nil )
        [result addValidationRange:[self.type validate]];
    if(self.bodySite != nil )
        for(FHIRCodeableConcept *elem in self.bodySite)
            [result addValidationRange:[elem validate]];
    if(self.indication != nil )
        for(FHIRCodeableConcept *elem in self.indication)
            [result addValidationRange:[elem validate]];
    if(self.performer != nil )
        for(FHIRProcedurePerformerComponent *elem in self.performer)
            [result addValidationRange:[elem validate]];
    if(self.date != nil )
        [result addValidationRange:[self.date validate]];
    if(self.encounter != nil )
        [result addValidationRange:[self.encounter validate]];
    if(self.outcomeElement != nil )
        [result addValidationRange:[self.outcomeElement validate]];
    if(self.report != nil )
        for(FHIRResourceReference *elem in self.report)
            [result addValidationRange:[elem validate]];
    if(self.complication != nil )
        for(FHIRCodeableConcept *elem in self.complication)
            [result addValidationRange:[elem validate]];
    if(self.followUpElement != nil )
        [result addValidationRange:[self.followUpElement validate]];
    if(self.relatedItem != nil )
        for(FHIRProcedureRelatedItemComponent *elem in self.relatedItem)
            [result addValidationRange:[elem validate]];
    if(self.notesElement != nil )
        [result addValidationRange:[self.notesElement validate]];
    
    return result;
}

@end
