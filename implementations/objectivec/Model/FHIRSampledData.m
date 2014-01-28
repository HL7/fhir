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
 * A series of measurements taken by a device
 */
#import "FHIRSampledData.h"

#import "FHIRQuantity.h"
#import "FHIRDecimal.h"
#import "FHIRInteger.h"
#import "FHIRString.h"

#import "FHIRErrorList.h"

@implementation FHIRSampledData

- (NSDecimalNumber *)period
{
    if(self.periodElement)
    {
        return [self.periodElement value];
    }
    return nil;
}

- (void )setPeriod:(NSDecimalNumber *)period
{
    if(period)
    {
        [self setPeriodElement:[[FHIRDecimal alloc] initWithValue:period]];
    }
    else
    {
        [self setPeriodElement:nil];
    }
}


- (NSDecimalNumber *)factor
{
    if(self.factorElement)
    {
        return [self.factorElement value];
    }
    return nil;
}

- (void )setFactor:(NSDecimalNumber *)factor
{
    if(factor)
    {
        [self setFactorElement:[[FHIRDecimal alloc] initWithValue:factor]];
    }
    else
    {
        [self setFactorElement:nil];
    }
}


- (NSDecimalNumber *)lowerLimit
{
    if(self.lowerLimitElement)
    {
        return [self.lowerLimitElement value];
    }
    return nil;
}

- (void )setLowerLimit:(NSDecimalNumber *)lowerLimit
{
    if(lowerLimit)
    {
        [self setLowerLimitElement:[[FHIRDecimal alloc] initWithValue:lowerLimit]];
    }
    else
    {
        [self setLowerLimitElement:nil];
    }
}


- (NSDecimalNumber *)upperLimit
{
    if(self.upperLimitElement)
    {
        return [self.upperLimitElement value];
    }
    return nil;
}

- (void )setUpperLimit:(NSDecimalNumber *)upperLimit
{
    if(upperLimit)
    {
        [self setUpperLimitElement:[[FHIRDecimal alloc] initWithValue:upperLimit]];
    }
    else
    {
        [self setUpperLimitElement:nil];
    }
}


- (NSNumber *)dimensions
{
    if(self.dimensionsElement)
    {
        return [self.dimensionsElement value];
    }
    return nil;
}

- (void )setDimensions:(NSNumber *)dimensions
{
    if(dimensions)
    {
        [self setDimensionsElement:[[FHIRInteger alloc] initWithValue:dimensions]];
    }
    else
    {
        [self setDimensionsElement:nil];
    }
}


- (NSString *)data
{
    if(self.dataElement)
    {
        return [self.dataElement value];
    }
    return nil;
}

- (void )setData:(NSString *)data
{
    if(data)
    {
        [self setDataElement:[[FHIRString alloc] initWithValue:data]];
    }
    else
    {
        [self setDataElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.origin != nil )
        [result addValidationRange:[self.origin validate]];
    if(self.periodElement != nil )
        [result addValidationRange:[self.periodElement validate]];
    if(self.factorElement != nil )
        [result addValidationRange:[self.factorElement validate]];
    if(self.lowerLimitElement != nil )
        [result addValidationRange:[self.lowerLimitElement validate]];
    if(self.upperLimitElement != nil )
        [result addValidationRange:[self.upperLimitElement validate]];
    if(self.dimensionsElement != nil )
        [result addValidationRange:[self.dimensionsElement validate]];
    if(self.dataElement != nil )
        [result addValidationRange:[self.dataElement validate]];
    
    return result;
}

@end
