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
 * A measured or measurable amount
 */
#import "FHIRQuantity.h"

#import "FHIRDecimal.h"
#import "FHIRCode.h"
#import "FHIRString.h"
#import "FHIRUri.h"

#import "FHIRErrorList.h"

@implementation FHIRQuantity

- (NSDecimalNumber *)value
{
    if(self.valueElement)
    {
        return [self.valueElement value];
    }
    return nil;
}

- (void )setValue:(NSDecimalNumber *)value
{
    if(value)
    {
        [self setValueElement:[[FHIRDecimal alloc] initWithValue:value]];
    }
    else
    {
        [self setValueElement:nil];
    }
}


- (kQuantityCompararator )comparator
{
    return [FHIREnumHelper parseString:[self.comparatorElement value] enumType:kEnumTypeQuantityCompararator];
}

- (void )setComparator:(kQuantityCompararator )comparator
{
    [self setComparatorElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:comparator enumType:kEnumTypeQuantityCompararator]]];
}


- (NSString *)units
{
    if(self.unitsElement)
    {
        return [self.unitsElement value];
    }
    return nil;
}

- (void )setUnits:(NSString *)units
{
    if(units)
    {
        [self setUnitsElement:[[FHIRString alloc] initWithValue:units]];
    }
    else
    {
        [self setUnitsElement:nil];
    }
}


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


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.valueElement != nil )
        [result addValidationRange:[self.valueElement validate]];
    if(self.comparatorElement != nil )
        [result addValidationRange:[self.comparatorElement validate]];
    if(self.unitsElement != nil )
        [result addValidationRange:[self.unitsElement validate]];
    if(self.systemElement != nil )
        [result addValidationRange:[self.systemElement validate]];
    if(self.codeElement != nil )
        [result addValidationRange:[self.codeElement validate]];
    
    return result;
}

@end
