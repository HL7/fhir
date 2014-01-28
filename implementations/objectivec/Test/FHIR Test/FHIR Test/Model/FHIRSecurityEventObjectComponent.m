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
#import "FHIRSecurityEventObjectComponent.h"

#import "FHIRIdentifier.h"
#import "FHIRResourceReference.h"
#import "FHIRCode.h"
#import "FHIRCodeableConcept.h"
#import "FHIRString.h"
#import "FHIRBase64Binary.h"
#import "FHIRSecurityEventObjectDetailComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRSecurityEventObjectComponent

- (kSecurityEventObjectType )type
{
    return [FHIREnumHelper parseString:[self.typeElement value] enumType:kEnumTypeSecurityEventObjectType];
}

- (void )setType:(kSecurityEventObjectType )type
{
    [self setTypeElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:type enumType:kEnumTypeSecurityEventObjectType]]];
}


- (kSecurityEventObjectRole )role
{
    return [FHIREnumHelper parseString:[self.roleElement value] enumType:kEnumTypeSecurityEventObjectRole];
}

- (void )setRole:(kSecurityEventObjectRole )role
{
    [self setRoleElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:role enumType:kEnumTypeSecurityEventObjectRole]]];
}


- (kSecurityEventObjectLifecycle )lifecycle
{
    return [FHIREnumHelper parseString:[self.lifecycleElement value] enumType:kEnumTypeSecurityEventObjectLifecycle];
}

- (void )setLifecycle:(kSecurityEventObjectLifecycle )lifecycle
{
    [self setLifecycleElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:lifecycle enumType:kEnumTypeSecurityEventObjectLifecycle]]];
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


- (NSString *)description
{
    if(self.descriptionElement)
    {
        return [self.descriptionElement value];
    }
    return nil;
}

- (void )setDescription:(NSString *)description
{
    if(description)
    {
        [self setDescriptionElement:[[FHIRString alloc] initWithValue:description]];
    }
    else
    {
        [self setDescriptionElement:nil];
    }
}


- (NSData *)query
{
    if(self.queryElement)
    {
        return [self.queryElement value];
    }
    return nil;
}

- (void )setQuery:(NSData *)query
{
    if(query)
    {
        [self setQueryElement:[[FHIRBase64Binary alloc] initWithValue:query]];
    }
    else
    {
        [self setQueryElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identifier != nil )
        [result addValidationRange:[self.identifier validate]];
    if(self.reference != nil )
        [result addValidationRange:[self.reference validate]];
    if(self.typeElement != nil )
        [result addValidationRange:[self.typeElement validate]];
    if(self.roleElement != nil )
        [result addValidationRange:[self.roleElement validate]];
    if(self.lifecycleElement != nil )
        [result addValidationRange:[self.lifecycleElement validate]];
    if(self.sensitivity != nil )
        [result addValidationRange:[self.sensitivity validate]];
    if(self.nameElement != nil )
        [result addValidationRange:[self.nameElement validate]];
    if(self.descriptionElement != nil )
        [result addValidationRange:[self.descriptionElement validate]];
    if(self.queryElement != nil )
        [result addValidationRange:[self.queryElement validate]];
    if(self.detail != nil )
        for(FHIRSecurityEventObjectDetailComponent *elem in self.detail)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
