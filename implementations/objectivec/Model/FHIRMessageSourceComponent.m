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
 * null
 */
#import "FHIRMessageSourceComponent.h"

#import "FHIRString.h"
#import "FHIRContact.h"
#import "FHIRUri.h"

#import "FHIRErrorList.h"

@implementation FHIRMessageSourceComponent

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


- (NSString *)software
{
    if(self.softwareElement)
    {
        return [self.softwareElement value];
    }
    return nil;
}

- (void )setSoftware:(NSString *)software
{
    if(software)
    {
        [self setSoftwareElement:[[FHIRString alloc] initWithValue:software]];
    }
    else
    {
        [self setSoftwareElement:nil];
    }
}


- (NSString *)version
{
    if(self.versionElement)
    {
        return [self.versionElement value];
    }
    return nil;
}

- (void )setVersion:(NSString *)version
{
    if(version)
    {
        [self setVersionElement:[[FHIRString alloc] initWithValue:version]];
    }
    else
    {
        [self setVersionElement:nil];
    }
}


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


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.nameElement != nil )
        [result addValidationRange:[self.nameElement validate]];
    if(self.softwareElement != nil )
        [result addValidationRange:[self.softwareElement validate]];
    if(self.versionElement != nil )
        [result addValidationRange:[self.versionElement validate]];
    if(self.contact != nil )
        [result addValidationRange:[self.contact validate]];
    if(self.endpointElement != nil )
        [result addValidationRange:[self.endpointElement validate]];
    
    return result;
}

@end
