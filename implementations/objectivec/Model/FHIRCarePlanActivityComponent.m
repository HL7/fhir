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
 * null
 */
#import "FHIRCarePlanActivityComponent.h"

#import "FHIRIdref.h"
#import "FHIRCode.h"
#import "FHIRBoolean.h"
#import "FHIRResourceReference.h"
#import "FHIRString.h"
#import "FHIRCarePlanActivitySimpleComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRCarePlanActivityComponent

- (NSArray /*<NSString>*/ *)goal
{
    if(self.goalElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRIdref *elem in self.goalElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setGoal:(NSArray /*<NSString>*/ *)goal
{
    if(goal)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in goal)
            [array addObject:[[FHIRIdref alloc] initWithValue:value]];
        [self setGoalElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setGoalElement:nil];
    }
}


- (kCarePlanActivityStatus )status
{
    return [FHIREnumHelper parseString:[self.statusElement value] enumType:kEnumTypeCarePlanActivityStatus];
}

- (void )setStatus:(kCarePlanActivityStatus )status
{
    [self setStatusElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:status enumType:kEnumTypeCarePlanActivityStatus]]];
}


- (NSNumber *)prohibited
{
    if(self.prohibitedElement)
    {
        return [self.prohibitedElement value];
    }
    return nil;
}

- (void )setProhibited:(NSNumber *)prohibited
{
    if(prohibited)
    {
        [self setProhibitedElement:[[FHIRBoolean alloc] initWithValue:prohibited]];
    }
    else
    {
        [self setProhibitedElement:nil];
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
    
    if(self.goalElement != nil )
        for(FHIRIdref *elem in self.goalElement)
            [result addValidationRange:[elem validate]];
    if(self.statusElement != nil )
        [result addValidationRange:[self.statusElement validate]];
    if(self.prohibitedElement != nil )
        [result addValidationRange:[self.prohibitedElement validate]];
    if(self.actionResulting != nil )
        for(FHIRResourceReference *elem in self.actionResulting)
            [result addValidationRange:[elem validate]];
    if(self.notesElement != nil )
        [result addValidationRange:[self.notesElement validate]];
    if(self.detail != nil )
        [result addValidationRange:[self.detail validate]];
    if(self.simple != nil )
        [result addValidationRange:[self.simple validate]];
    
    return result;
}

@end
