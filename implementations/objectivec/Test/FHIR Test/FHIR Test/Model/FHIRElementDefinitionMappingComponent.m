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
#import "FHIRElementDefinitionMappingComponent.h"

#import "FHIRId.h"
#import "FHIRString.h"

#import "FHIRErrorList.h"

@implementation FHIRElementDefinitionMappingComponent

- (NSString *)identity
{
    if(self.identityElement)
    {
        return [self.identityElement value];
    }
    return nil;
}

- (void )setIdentity:(NSString *)identity
{
    if(identity)
    {
        [self setIdentityElement:[[FHIRId alloc] initWithValue:identity]];
    }
    else
    {
        [self setIdentityElement:nil];
    }
}


- (NSString *)map
{
    if(self.mapElement)
    {
        return [self.mapElement value];
    }
    return nil;
}

- (void )setMap:(NSString *)map
{
    if(map)
    {
        [self setMapElement:[[FHIRString alloc] initWithValue:map]];
    }
    else
    {
        [self setMapElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identityElement != nil )
        [result addValidationRange:[self.identityElement validate]];
    if(self.mapElement != nil )
        [result addValidationRange:[self.mapElement validate]];
    
    return result;
}

@end
