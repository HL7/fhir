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
 * Name of a human - parts and usage
 */
#import "FHIRHumanName.h"

#import "FHIRCode.h"
#import "FHIRString.h"
#import "FHIRPeriod.h"

#import "FHIRErrorList.h"

@implementation FHIRHumanName

- (kNameUse )use
{
    return [FHIREnumHelper parseString:[self.useElement value] enumType:kEnumTypeNameUse];
}

- (void )setUse:(kNameUse )use
{
    [self setUseElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:use enumType:kEnumTypeNameUse]]];
}


- (NSString *)text
{
    if(self.textElement)
    {
        return [self.textElement value];
    }
    return nil;
}

- (void )setText:(NSString *)text
{
    if(text)
    {
        [self setTextElement:[[FHIRString alloc] initWithValue:text]];
    }
    else
    {
        [self setTextElement:nil];
    }
}


- (NSArray /*<NSString>*/ *)family
{
    if(self.familyElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRString *elem in self.familyElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setFamily:(NSArray /*<NSString>*/ *)family
{
    if(family)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in family)
            [array addObject:[[FHIRString alloc] initWithValue:value]];
        [self setFamilyElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setFamilyElement:nil];
    }
}


- (NSArray /*<NSString>*/ *)given
{
    if(self.givenElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRString *elem in self.givenElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setGiven:(NSArray /*<NSString>*/ *)given
{
    if(given)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in given)
            [array addObject:[[FHIRString alloc] initWithValue:value]];
        [self setGivenElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setGivenElement:nil];
    }
}


- (NSArray /*<NSString>*/ *)prefix
{
    if(self.prefixElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRString *elem in self.prefixElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setPrefix:(NSArray /*<NSString>*/ *)prefix
{
    if(prefix)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in prefix)
            [array addObject:[[FHIRString alloc] initWithValue:value]];
        [self setPrefixElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setPrefixElement:nil];
    }
}


- (NSArray /*<NSString>*/ *)suffix
{
    if(self.suffixElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRString *elem in self.suffixElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setSuffix:(NSArray /*<NSString>*/ *)suffix
{
    if(suffix)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in suffix)
            [array addObject:[[FHIRString alloc] initWithValue:value]];
        [self setSuffixElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setSuffixElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.useElement != nil )
        [result addValidationRange:[self.useElement validate]];
    if(self.textElement != nil )
        [result addValidationRange:[self.textElement validate]];
    if(self.familyElement != nil )
        for(FHIRString *elem in self.familyElement)
            [result addValidationRange:[elem validate]];
    if(self.givenElement != nil )
        for(FHIRString *elem in self.givenElement)
            [result addValidationRange:[elem validate]];
    if(self.prefixElement != nil )
        for(FHIRString *elem in self.prefixElement)
            [result addValidationRange:[elem validate]];
    if(self.suffixElement != nil )
        for(FHIRString *elem in self.suffixElement)
            [result addValidationRange:[elem validate]];
    if(self.period != nil )
        [result addValidationRange:[self.period validate]];
    
    return result;
}

@end
