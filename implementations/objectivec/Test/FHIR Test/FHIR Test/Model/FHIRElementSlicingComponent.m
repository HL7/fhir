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
#import "FHIRElementSlicingComponent.h"

#import "FHIRId.h"
#import "FHIRBoolean.h"
#import "FHIRCode.h"

#import "FHIRErrorList.h"

@implementation FHIRElementSlicingComponent

- (NSString *)discriminator
{
    if(self.discriminatorElement)
    {
        return [self.discriminatorElement value];
    }
    return nil;
}

- (void )setDiscriminator:(NSString *)discriminator
{
    if(discriminator)
    {
        [self setDiscriminatorElement:[[FHIRId alloc] initWithValue:discriminator]];
    }
    else
    {
        [self setDiscriminatorElement:nil];
    }
}


- (NSNumber *)ordered
{
    if(self.orderedElement)
    {
        return [self.orderedElement value];
    }
    return nil;
}

- (void )setOrdered:(NSNumber *)ordered
{
    if(ordered)
    {
        [self setOrderedElement:[[FHIRBoolean alloc] initWithValue:ordered]];
    }
    else
    {
        [self setOrderedElement:nil];
    }
}


- (kSlicingRules )rules
{
    return [FHIREnumHelper parseString:[self.rulesElement value] enumType:kEnumTypeSlicingRules];
}

- (void )setRules:(kSlicingRules )rules
{
    [self setRulesElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:rules enumType:kEnumTypeSlicingRules]]];
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.discriminatorElement != nil )
        [result addValidationRange:[self.discriminatorElement validate]];
    if(self.orderedElement != nil )
        [result addValidationRange:[self.orderedElement validate]];
    if(self.rulesElement != nil )
        [result addValidationRange:[self.rulesElement validate]];
    
    return result;
}

@end
