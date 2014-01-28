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
 * null
 */
#import "FHIRValueSetDefineConceptComponent.h"

#import "FHIRCode.h"
#import "FHIRBoolean.h"
#import "FHIRString.h"
#import "FHIRValueSetDefineConceptComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRValueSetDefineConceptComponent

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


- (NSNumber *)abstract_
{
    if(self.abstract_Element)
    {
        return [self.abstract_Element value];
    }
    return nil;
}

- (void )setAbstract_:(NSNumber *)abstract_
{
    if(abstract_)
    {
        [self setAbstract_Element:[[FHIRBoolean alloc] initWithValue:abstract_]];
    }
    else
    {
        [self setAbstract_Element:nil];
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


- (NSString *)definition
{
    if(self.definitionElement)
    {
        return [self.definitionElement value];
    }
    return nil;
}

- (void )setDefinition:(NSString *)definition
{
    if(definition)
    {
        [self setDefinitionElement:[[FHIRString alloc] initWithValue:definition]];
    }
    else
    {
        [self setDefinitionElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.codeElement != nil )
        [result addValidationRange:[self.codeElement validate]];
    if(self.abstract_Element != nil )
        [result addValidationRange:[self.abstract_Element validate]];
    if(self.displayElement != nil )
        [result addValidationRange:[self.displayElement validate]];
    if(self.definitionElement != nil )
        [result addValidationRange:[self.definitionElement validate]];
    if(self.concept != nil )
        for(FHIRValueSetDefineConceptComponent *elem in self.concept)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
