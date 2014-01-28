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
#import "FHIRConformanceMessagingComponent.h"

#import "FHIRUri.h"
#import "FHIRInteger.h"
#import "FHIRString.h"
#import "FHIRConformanceMessagingEventComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRConformanceMessagingComponent

- (NSString *)endpoint
{
    if(self.endpointElement)
    {
        return [self.endpointElement value];
    }
    return nil;
}

- (void )setEndpoint:(NSString *)endpoint
{
    if(endpoint)
    {
        [self setEndpointElement:[[FHIRUri alloc] initWithValue:endpoint]];
    }
    else
    {
        [self setEndpointElement:nil];
    }
}


- (NSNumber *)reliableCache
{
    if(self.reliableCacheElement)
    {
        return [self.reliableCacheElement value];
    }
    return nil;
}

- (void )setReliableCache:(NSNumber *)reliableCache
{
    if(reliableCache)
    {
        [self setReliableCacheElement:[[FHIRInteger alloc] initWithValue:reliableCache]];
    }
    else
    {
        [self setReliableCacheElement:nil];
    }
}


- (NSString *)documentation
{
    if(self.documentationElement)
    {
        return [self.documentationElement value];
    }
    return nil;
}

- (void )setDocumentation:(NSString *)documentation
{
    if(documentation)
    {
        [self setDocumentationElement:[[FHIRString alloc] initWithValue:documentation]];
    }
    else
    {
        [self setDocumentationElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.endpointElement != nil )
        [result addValidationRange:[self.endpointElement validate]];
    if(self.reliableCacheElement != nil )
        [result addValidationRange:[self.reliableCacheElement validate]];
    if(self.documentationElement != nil )
        [result addValidationRange:[self.documentationElement validate]];
    if(self.event_ != nil )
        for(FHIRConformanceMessagingEventComponent *elem in self.event_)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
