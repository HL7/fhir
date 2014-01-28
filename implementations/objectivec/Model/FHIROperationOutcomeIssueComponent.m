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
#import "FHIROperationOutcomeIssueComponent.h"

#import "FHIRCode.h"
#import "FHIRCoding.h"
#import "FHIRString.h"

#import "FHIRErrorList.h"

@implementation FHIROperationOutcomeIssueComponent

- (kIssueSeverity )severity
{
    return [FHIREnumHelper parseString:[self.severityElement value] enumType:kEnumTypeIssueSeverity];
}

- (void )setSeverity:(kIssueSeverity )severity
{
    [self setSeverityElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:severity enumType:kEnumTypeIssueSeverity]]];
}


- (NSString *)details
{
    if(self.detailsElement)
    {
        return [self.detailsElement value];
    }
    return nil;
}

- (void )setDetails:(NSString *)details
{
    if(details)
    {
        [self setDetailsElement:[[FHIRString alloc] initWithValue:details]];
    }
    else
    {
        [self setDetailsElement:nil];
    }
}


- (NSArray /*<NSString>*/ *)location
{
    if(self.locationElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRString *elem in self.locationElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setLocation:(NSArray /*<NSString>*/ *)location
{
    if(location)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in location)
            [array addObject:[[FHIRString alloc] initWithValue:value]];
        [self setLocationElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setLocationElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.severityElement != nil )
        [result addValidationRange:[self.severityElement validate]];
    if(self.type != nil )
        [result addValidationRange:[self.type validate]];
    if(self.detailsElement != nil )
        [result addValidationRange:[self.detailsElement validate]];
    if(self.locationElement != nil )
        for(FHIRString *elem in self.locationElement)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
