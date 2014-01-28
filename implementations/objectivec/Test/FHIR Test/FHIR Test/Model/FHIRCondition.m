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
 * Detailed information about conditions, problems or diagnoses
 */
#import "FHIRCondition.h"

#import "FHIRIdentifier.h"
#import "FHIRResourceReference.h"
#import "FHIRDate.h"
#import "FHIRCodeableConcept.h"
#import "FHIRCode.h"
#import "FHIRElement.h"
#import "FHIRConditionStageComponent.h"
#import "FHIRConditionEvidenceComponent.h"
#import "FHIRConditionLocationComponent.h"
#import "FHIRConditionRelatedItemComponent.h"
#import "FHIRString.h"

#import "FHIRErrorList.h"

@implementation FHIRCondition

- (NSString *)dateAsserted
{
    if(self.dateAssertedElement)
    {
        return [self.dateAssertedElement value];
    }
    return nil;
}

- (void )setDateAsserted:(NSString *)dateAsserted
{
    if(dateAsserted)
    {
        [self setDateAssertedElement:[[FHIRDate alloc] initWithValue:dateAsserted]];
    }
    else
    {
        [self setDateAssertedElement:nil];
    }
}


- (kConditionStatus )status
{
    return [FHIREnumHelper parseString:[self.statusElement value] enumType:kEnumTypeConditionStatus];
}

- (void )setStatus:(kConditionStatus )status
{
    [self setStatusElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:status enumType:kEnumTypeConditionStatus]]];
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
    if(self.encounter != nil )
        [result addValidationRange:[self.encounter validate]];
    if(self.asserter != nil )
        [result addValidationRange:[self.asserter validate]];
    if(self.dateAssertedElement != nil )
        [result addValidationRange:[self.dateAssertedElement validate]];
    if(self.code != nil )
        [result addValidationRange:[self.code validate]];
    if(self.category != nil )
        [result addValidationRange:[self.category validate]];
    if(self.statusElement != nil )
        [result addValidationRange:[self.statusElement validate]];
    if(self.certainty != nil )
        [result addValidationRange:[self.certainty validate]];
    if(self.severity != nil )
        [result addValidationRange:[self.severity validate]];
    if(self.onset != nil )
        [result addValidationRange:[self.onset validate]];
    if(self.abatement != nil )
        [result addValidationRange:[self.abatement validate]];
    if(self.stage != nil )
        [result addValidationRange:[self.stage validate]];
    if(self.evidence != nil )
        for(FHIRConditionEvidenceComponent *elem in self.evidence)
            [result addValidationRange:[elem validate]];
    if(self.location != nil )
        for(FHIRConditionLocationComponent *elem in self.location)
            [result addValidationRange:[elem validate]];
    if(self.relatedItem != nil )
        for(FHIRConditionRelatedItemComponent *elem in self.relatedItem)
            [result addValidationRange:[elem validate]];
    if(self.notesElement != nil )
        [result addValidationRange:[self.notesElement validate]];
    
    return result;
}

@end
