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
#import "FHIRElementDefinitionConstraintComponent.h"

#import "FHIRId.h"
#import "FHIRString.h"
#import "FHIRCode.h"

#import "FHIRErrorList.h"

@implementation FHIRElementDefinitionConstraintComponent

- (NSString *)key
{
    if(self.keyElement)
    {
        return [self.keyElement value];
    }
    return nil;
}

- (void )setKey:(NSString *)key
{
    if(key)
    {
        [self setKeyElement:[[FHIRId alloc] initWithValue:key]];
    }
    else
    {
        [self setKeyElement:nil];
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


- (kConstraintSeverity )severity
{
    return [FHIREnumHelper parseString:[self.severityElement value] enumType:kEnumTypeConstraintSeverity];
}

- (void )setSeverity:(kConstraintSeverity )severity
{
    [self setSeverityElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:severity enumType:kEnumTypeConstraintSeverity]]];
}


- (NSString *)human
{
    if(self.humanElement)
    {
        return [self.humanElement value];
    }
    return nil;
}

- (void )setHuman:(NSString *)human
{
    if(human)
    {
        [self setHumanElement:[[FHIRString alloc] initWithValue:human]];
    }
    else
    {
        [self setHumanElement:nil];
    }
}


- (NSString *)xpath
{
    if(self.xpathElement)
    {
        return [self.xpathElement value];
    }
    return nil;
}

- (void )setXpath:(NSString *)xpath
{
    if(xpath)
    {
        [self setXpathElement:[[FHIRString alloc] initWithValue:xpath]];
    }
    else
    {
        [self setXpathElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.keyElement != nil )
        [result addValidationRange:[self.keyElement validate]];
    if(self.nameElement != nil )
        [result addValidationRange:[self.nameElement validate]];
    if(self.severityElement != nil )
        [result addValidationRange:[self.severityElement validate]];
    if(self.humanElement != nil )
        [result addValidationRange:[self.humanElement validate]];
    if(self.xpathElement != nil )
        [result addValidationRange:[self.xpathElement validate]];
    
    return result;
}

@end
