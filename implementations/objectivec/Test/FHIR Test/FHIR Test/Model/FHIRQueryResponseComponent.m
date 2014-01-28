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
 * null
 */
#import "FHIRQueryResponseComponent.h"

#import "FHIRUri.h"
#import "FHIRCode.h"
#import "FHIRInteger.h"
#import "FHIRExtension.h"
#import "FHIRResourceReference.h"

#import "FHIRErrorList.h"

@implementation FHIRQueryResponseComponent

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
        [self setIdentifierElement:[[FHIRUri alloc] initWithValue:identifier]];
    }
    else
    {
        [self setIdentifierElement:nil];
    }
}


- (kQueryOutcome )outcome
{
    return [FHIREnumHelper parseString:[self.outcomeElement value] enumType:kEnumTypeQueryOutcome];
}

- (void )setOutcome:(kQueryOutcome )outcome
{
    [self setOutcomeElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:outcome enumType:kEnumTypeQueryOutcome]]];
}


- (NSNumber *)total
{
    if(self.totalElement)
    {
        return [self.totalElement value];
    }
    return nil;
}

- (void )setTotal:(NSNumber *)total
{
    if(total)
    {
        [self setTotalElement:[[FHIRInteger alloc] initWithValue:total]];
    }
    else
    {
        [self setTotalElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identifierElement != nil )
        [result addValidationRange:[self.identifierElement validate]];
    if(self.outcomeElement != nil )
        [result addValidationRange:[self.outcomeElement validate]];
    if(self.totalElement != nil )
        [result addValidationRange:[self.totalElement validate]];
    if(self.parameter != nil )
        for(FHIRExtension *elem in self.parameter)
            [result addValidationRange:[elem validate]];
    if(self.first != nil )
        for(FHIRExtension *elem in self.first)
            [result addValidationRange:[elem validate]];
    if(self.previous != nil )
        for(FHIRExtension *elem in self.previous)
            [result addValidationRange:[elem validate]];
    if(self.next != nil )
        for(FHIRExtension *elem in self.next)
            [result addValidationRange:[elem validate]];
    if(self.last != nil )
        for(FHIRExtension *elem in self.last)
            [result addValidationRange:[elem validate]];
    if(self.reference != nil )
        for(FHIRResourceReference *elem in self.reference)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
