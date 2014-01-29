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
 * A resource that describes a message that is exchanged between systems
 */
#import "FHIRMessageHeader.h"

#import "FHIRId.h"
#import "FHIRInstant.h"
#import "FHIRCoding.h"
#import "FHIRMessageHeaderResponseComponent.h"
#import "FHIRMessageSourceComponent.h"
#import "FHIRMessageDestinationComponent.h"
#import "FHIRResourceReference.h"
#import "FHIRCodeableConcept.h"

#import "FHIRErrorList.h"

@implementation FHIRMessageHeader

- (NSString *)identifier
{
    if(self.identifierElement)
    {
        return [self.identifierElement value];
    }
    return nil;
}

- (void )setIdentifier:(NSString *)identifier
{
    if(identifier)
    {
        [self setIdentifierElement:[[FHIRId alloc] initWithValue:identifier]];
    }
    else
    {
        [self setIdentifierElement:nil];
    }
}


- (NSDate *)timestamp
{
    if(self.timestampElement)
    {
        return [self.timestampElement value];
    }
    return nil;
}

- (void )setTimestamp:(NSDate *)timestamp
{
    if(timestamp)
    {
        [self setTimestampElement:[[FHIRInstant alloc] initWithValue:timestamp]];
    }
    else
    {
        [self setTimestampElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identifierElement != nil )
        [result addValidationRange:[self.identifierElement validate]];
    if(self.timestampElement != nil )
        [result addValidationRange:[self.timestampElement validate]];
    if(self.event_ != nil )
        [result addValidationRange:[self.event_ validate]];
    if(self.response != nil )
        [result addValidationRange:[self.response validate]];
    if(self.source != nil )
        [result addValidationRange:[self.source validate]];
    if(self.destination != nil )
        for(FHIRMessageDestinationComponent *elem in self.destination)
            [result addValidationRange:[elem validate]];
    if(self.enterer != nil )
        [result addValidationRange:[self.enterer validate]];
    if(self.author != nil )
        [result addValidationRange:[self.author validate]];
    if(self.receiver != nil )
        [result addValidationRange:[self.receiver validate]];
    if(self.responsible != nil )
        [result addValidationRange:[self.responsible validate]];
    if(self.reason != nil )
        [result addValidationRange:[self.reason validate]];
    if(self.data != nil )
        for(FHIRResourceReference *elem in self.data)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
