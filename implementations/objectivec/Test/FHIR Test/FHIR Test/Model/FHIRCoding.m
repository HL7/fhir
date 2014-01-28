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
 * A reference to a code defined by a terminology system
 */
#import "FHIRCoding.h"

#import "FHIRUri.h"
#import "FHIRString.h"
#import "FHIRCode.h"
#import "FHIRBoolean.h"
#import "FHIRResourceReference.h"

#import "FHIRErrorList.h"

@implementation FHIRCoding

- (NSString *)system
{
    if(self.systemElement)
    {
        return [self.systemElement value];
    }
    return nil;
}

- (void )setSystem:(NSString *)system
{
    if(system)
    {
        [self setSystemElement:[[FHIRUri alloc] initWithValue:system]];
    }
    else
    {
        [self setSystemElement:nil];
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


- (NSString *)code
{
    if(self.codeElement)
    {
        return [self.codeElement value];
    }
    return nil;
}

- (void )setCode:(NSString *)code
{
    if(code)
    {
        [self setCodeElement:[[FHIRCode alloc] initWithValue:code]];
    }
    else
    {
        [self setCodeElement:nil];
    }
}


- (NSString *)display
{
    if(self.displayElement)
    {
        return [self.displayElement value];
    }
    return nil;
}

- (void )setDisplay:(NSString *)display
{
    if(display)
    {
        [self setDisplayElement:[[FHIRString alloc] initWithValue:display]];
    }
    else
    {
        [self setDisplayElement:nil];
    }
}


- (NSNumber *)primary
{
    if(self.primaryElement)
    {
        return [self.primaryElement value];
    }
    return nil;
}

- (void )setPrimary:(NSNumber *)primary
{
    if(primary)
    {
        [self setPrimaryElement:[[FHIRBoolean alloc] initWithValue:primary]];
    }
    else
    {
        [self setPrimaryElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.systemElement != nil )
        [result addValidationRange:[self.systemElement validate]];
    if(self.versionElement != nil )
        [result addValidationRange:[self.versionElement validate]];
    if(self.codeElement != nil )
        [result addValidationRange:[self.codeElement validate]];
    if(self.displayElement != nil )
        [result addValidationRange:[self.displayElement validate]];
    if(self.primaryElement != nil )
        [result addValidationRange:[self.primaryElement validate]];
    if(self.valueSet != nil )
        [result addValidationRange:[self.valueSet validate]];
    
    return result;
}

@end
