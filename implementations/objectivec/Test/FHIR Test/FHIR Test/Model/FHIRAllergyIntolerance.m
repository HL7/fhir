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
 * Drug, food, environmental and others
 */
#import "FHIRAllergyIntolerance.h"

#import "FHIRIdentifier.h"
#import "FHIRCode.h"
#import "FHIRDateTime.h"
#import "FHIRResourceReference.h"

#import "FHIRErrorList.h"

@implementation FHIRAllergyIntolerance

- (kCriticality )criticality
{
    return [FHIREnumHelper parseString:[self.criticalityElement value] enumType:kEnumTypeCriticality];
}

- (void )setCriticality:(kCriticality )criticality
{
    [self setCriticalityElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:criticality enumType:kEnumTypeCriticality]]];
}


- (kSensitivityType )sensitivityType
{
    return [FHIREnumHelper parseString:[self.sensitivityTypeElement value] enumType:kEnumTypeSensitivityType];
}

- (void )setSensitivityType:(kSensitivityType )sensitivityType
{
    [self setSensitivityTypeElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:sensitivityType enumType:kEnumTypeSensitivityType]]];
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


- (kSensitivityStatus )status
{
    return [FHIREnumHelper parseString:[self.statusElement value] enumType:kEnumTypeSensitivityStatus];
}

- (void )setStatus:(kSensitivityStatus )status
{
    [self setStatusElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:status enumType:kEnumTypeSensitivityStatus]]];
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identifier != nil )
        for(FHIRIdentifier *elem in self.identifier)
            [result addValidationRange:[elem validate]];
    if(self.criticalityElement != nil )
        [result addValidationRange:[self.criticalityElement validate]];
    if(self.sensitivityTypeElement != nil )
        [result addValidationRange:[self.sensitivityTypeElement validate]];
    if(self.recordedDateElement != nil )
        [result addValidationRange:[self.recordedDateElement validate]];
    if(self.statusElement != nil )
        [result addValidationRange:[self.statusElement validate]];
    if(self.subject != nil )
        [result addValidationRange:[self.subject validate]];
    if(self.recorder != nil )
        [result addValidationRange:[self.recorder validate]];
    if(self.substance != nil )
        [result addValidationRange:[self.substance validate]];
    if(self.reaction != nil )
        for(FHIRResourceReference *elem in self.reaction)
            [result addValidationRange:[elem validate]];
    if(self.sensitivityTest != nil )
        for(FHIRResourceReference *elem in self.sensitivityTest)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
