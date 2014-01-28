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
#import "FHIRConformanceRestResourceSearchParamComponent.h"

#import "FHIRString.h"
#import "FHIRUri.h"
#import "FHIRCode.h"

#import "FHIRErrorList.h"

@implementation FHIRConformanceRestResourceSearchParamComponent

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


- (NSString *)source
{
    if(self.sourceElement)
    {
        return [self.sourceElement value];
    }
    return nil;
}

- (void )setSource:(NSString *)source
{
    if(source)
    {
        [self setSourceElement:[[FHIRUri alloc] initWithValue:source]];
    }
    else
    {
        [self setSourceElement:nil];
    }
}


- (kSearchParamType )type
{
    return [FHIREnumHelper parseString:[self.typeElement value] enumType:kEnumTypeSearchParamType];
}

- (void )setType:(kSearchParamType )type
{
    [self setTypeElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:type enumType:kEnumTypeSearchParamType]]];
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


- (NSArray /*<NSString>*/ *)target
{
    if(self.targetElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRCode *elem in self.targetElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setTarget:(NSArray /*<NSString>*/ *)target
{
    if(target)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in target)
            [array addObject:[[FHIRCode alloc] initWithValue:value]];
        [self setTargetElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setTargetElement:nil];
    }
}


- (NSArray /*<NSString>*/ *)chain
{
    if(self.chainElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRString *elem in self.chainElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setChain:(NSArray /*<NSString>*/ *)chain
{
    if(chain)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in chain)
            [array addObject:[[FHIRString alloc] initWithValue:value]];
        [self setChainElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setChainElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.nameElement != nil )
        [result addValidationRange:[self.nameElement validate]];
    if(self.sourceElement != nil )
        [result addValidationRange:[self.sourceElement validate]];
    if(self.typeElement != nil )
        [result addValidationRange:[self.typeElement validate]];
    if(self.documentationElement != nil )
        [result addValidationRange:[self.documentationElement validate]];
    if(self.xpathElement != nil )
        [result addValidationRange:[self.xpathElement validate]];
    if(self.targetElement != nil )
        for(FHIRCode *elem in self.targetElement)
            [result addValidationRange:[elem validate]];
    if(self.chainElement != nil )
        for(FHIRString *elem in self.chainElement)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
