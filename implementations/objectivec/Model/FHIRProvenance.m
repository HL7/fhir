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
 * Who, What, When for a set of resources
 */
#import "FHIRProvenance.h"

#import "FHIRResourceReference.h"
#import "FHIRPeriod.h"
#import "FHIRInstant.h"
#import "FHIRCodeableConcept.h"
#import "FHIRUri.h"
#import "FHIRProvenanceAgentComponent.h"
#import "FHIRProvenanceEntityComponent.h"
#import "FHIRString.h"

#import "FHIRErrorList.h"

@implementation FHIRProvenance

- (NSDate *)recorded
{
    if(self.recordedElement)
    {
        return [self.recordedElement value];
    }
    return nil;
}

- (void )setRecorded:(NSDate *)recorded
{
    if(recorded)
    {
        [self setRecordedElement:[[FHIRInstant alloc] initWithValue:recorded]];
    }
    else
    {
        [self setRecordedElement:nil];
    }
}


- (NSArray /*<NSString>*/ *)policy
{
    if(self.policyElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRUri *elem in self.policyElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setPolicy:(NSArray /*<NSString>*/ *)policy
{
    if(policy)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in policy)
            [array addObject:[[FHIRUri alloc] initWithValue:value]];
        [self setPolicyElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setPolicyElement:nil];
    }
}


- (NSString *)integritySignature
{
    if(self.integritySignatureElement)
    {
        return [self.integritySignatureElement value];
    }
    return nil;
}

- (void )setIntegritySignature:(NSString *)integritySignature
{
    if(integritySignature)
    {
        [self setIntegritySignatureElement:[[FHIRString alloc] initWithValue:integritySignature]];
    }
    else
    {
        [self setIntegritySignatureElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.target != nil )
        for(FHIRResourceReference *elem in self.target)
            [result addValidationRange:[elem validate]];
    if(self.period != nil )
        [result addValidationRange:[self.period validate]];
    if(self.recordedElement != nil )
        [result addValidationRange:[self.recordedElement validate]];
    if(self.reason != nil )
        [result addValidationRange:[self.reason validate]];
    if(self.location != nil )
        [result addValidationRange:[self.location validate]];
    if(self.policyElement != nil )
        for(FHIRUri *elem in self.policyElement)
            [result addValidationRange:[elem validate]];
    if(self.agent != nil )
        for(FHIRProvenanceAgentComponent *elem in self.agent)
            [result addValidationRange:[elem validate]];
    if(self.entity != nil )
        for(FHIRProvenanceEntityComponent *elem in self.entity)
            [result addValidationRange:[elem validate]];
    if(self.integritySignatureElement != nil )
        [result addValidationRange:[self.integritySignatureElement validate]];
    
    return result;
}

@end
