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
 * A postal address
 */
#import "FHIRAddress.h"

#import "FHIRCode.h"
#import "FHIRString.h"
#import "FHIRPeriod.h"

#import "FHIRErrorList.h"

@implementation FHIRAddress

- (kAddressUse )use
{
    return [FHIREnumHelper parseString:[self.useElement value] enumType:kEnumTypeAddressUse];
}

- (void )setUse:(kAddressUse )use
{
    [self setUseElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:use enumType:kEnumTypeAddressUse]]];
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


- (NSArray /*<NSString>*/ *)line
{
    if(self.lineElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRString *elem in self.lineElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setLine:(NSArray /*<NSString>*/ *)line
{
    if(line)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in line)
            [array addObject:[[FHIRString alloc] initWithValue:value]];
        [self setLineElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setLineElement:nil];
    }
}


- (NSString *)city
{
    if(self.cityElement)
    {
        return [self.cityElement value];
    }
    return nil;
}

- (void )setCity:(NSString *)city
{
    if(city)
    {
        [self setCityElement:[[FHIRString alloc] initWithValue:city]];
    }
    else
    {
        [self setCityElement:nil];
    }
}


- (NSString *)state
{
    if(self.stateElement)
    {
        return [self.stateElement value];
    }
    return nil;
}

- (void )setState:(NSString *)state
{
    if(state)
    {
        [self setStateElement:[[FHIRString alloc] initWithValue:state]];
    }
    else
    {
        [self setStateElement:nil];
    }
}


- (NSString *)zip
{
    if(self.zipElement)
    {
        return [self.zipElement value];
    }
    return nil;
}

- (void )setZip:(NSString *)zip
{
    if(zip)
    {
        [self setZipElement:[[FHIRString alloc] initWithValue:zip]];
    }
    else
    {
        [self setZipElement:nil];
    }
}


- (NSString *)country
{
    if(self.countryElement)
    {
        return [self.countryElement value];
    }
    return nil;
}

- (void )setCountry:(NSString *)country
{
    if(country)
    {
        [self setCountryElement:[[FHIRString alloc] initWithValue:country]];
    }
    else
    {
        [self setCountryElement:nil];
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
    if(self.lineElement != nil )
        for(FHIRString *elem in self.lineElement)
            [result addValidationRange:[elem validate]];
    if(self.cityElement != nil )
        [result addValidationRange:[self.cityElement validate]];
    if(self.stateElement != nil )
        [result addValidationRange:[self.stateElement validate]];
    if(self.zipElement != nil )
        [result addValidationRange:[self.zipElement validate]];
    if(self.countryElement != nil )
        [result addValidationRange:[self.countryElement validate]];
    if(self.period != nil )
        [result addValidationRange:[self.period validate]];
    
    return result;
}

@end
