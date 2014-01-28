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
#import "FHIRSecurityEventParticipantComponent.h"

#import "FHIRCodeableConcept.h"
#import "FHIRResourceReference.h"
#import "FHIRString.h"
#import "FHIRBoolean.h"
#import "FHIRCoding.h"
#import "FHIRSecurityEventParticipantNetworkComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRSecurityEventParticipantComponent

- (NSString *)userId
{
    if(self.userIdElement)
    {
        return [self.userIdElement value];
    }
    return nil;
}

- (void )setUserId:(NSString *)userId
{
    if(userId)
    {
        [self setUserIdElement:[[FHIRString alloc] initWithValue:userId]];
    }
    else
    {
        [self setUserIdElement:nil];
    }
}


- (NSString *)altId
{
    if(self.altIdElement)
    {
        return [self.altIdElement value];
    }
    return nil;
}

- (void )setAltId:(NSString *)altId
{
    if(altId)
    {
        [self setAltIdElement:[[FHIRString alloc] initWithValue:altId]];
    }
    else
    {
        [self setAltIdElement:nil];
    }
}


- (NSString *)name
{
    if(self.nameElement)
    {
        return [self.nameElement value];
    }
    return nil;
}

- (void )setName:(NSString *)name
{
    if(name)
    {
        [self setNameElement:[[FHIRString alloc] initWithValue:name]];
    }
    else
    {
        [self setNameElement:nil];
    }
}


- (NSNumber *)requestor
{
    if(self.requestorElement)
    {
        return [self.requestorElement value];
    }
    return nil;
}

- (void )setRequestor:(NSNumber *)requestor
{
    if(requestor)
    {
        [self setRequestorElement:[[FHIRBoolean alloc] initWithValue:requestor]];
    }
    else
    {
        [self setRequestorElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.role != nil )
        for(FHIRCodeableConcept *elem in self.role)
            [result addValidationRange:[elem validate]];
    if(self.reference != nil )
        [result addValidationRange:[self.reference validate]];
    if(self.userIdElement != nil )
        [result addValidationRange:[self.userIdElement validate]];
    if(self.altIdElement != nil )
        [result addValidationRange:[self.altIdElement validate]];
    if(self.nameElement != nil )
        [result addValidationRange:[self.nameElement validate]];
    if(self.requestorElement != nil )
        [result addValidationRange:[self.requestorElement validate]];
    if(self.media != nil )
        [result addValidationRange:[self.media validate]];
    if(self.network != nil )
        [result addValidationRange:[self.network validate]];
    
    return result;
}

@end
