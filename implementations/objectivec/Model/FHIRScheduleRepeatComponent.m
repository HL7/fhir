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
#import "FHIRScheduleRepeatComponent.h"

#import "FHIRInteger.h"
#import "FHIRCode.h"
#import "FHIRDecimal.h"
#import "FHIRDateTime.h"

#import "FHIRErrorList.h"

@implementation FHIRScheduleRepeatComponent

- (NSNumber *)frequency
{
    if(self.frequencyElement)
    {
        return [self.frequencyElement value];
    }
    return nil;
}

- (void )setFrequency:(NSNumber *)frequency
{
    if(frequency)
    {
        [self setFrequencyElement:[[FHIRInteger alloc] initWithValue:frequency]];
    }
    else
    {
        [self setFrequencyElement:nil];
    }
}


- (kEventTiming )when
{
    return [FHIREnumHelper parseString:[self.whenElement value] enumType:kEnumTypeEventTiming];
}

- (void )setWhen:(kEventTiming )when
{
    [self setWhenElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:when enumType:kEnumTypeEventTiming]]];
}


- (NSDecimalNumber *)duration
{
    if(self.durationElement)
    {
        return [self.durationElement value];
    }
    return nil;
}

- (void )setDuration:(NSDecimalNumber *)duration
{
    if(duration)
    {
        [self setDurationElement:[[FHIRDecimal alloc] initWithValue:duration]];
    }
    else
    {
        [self setDurationElement:nil];
    }
}


- (kUnitsOfTime )units
{
    return [FHIREnumHelper parseString:[self.unitsElement value] enumType:kEnumTypeUnitsOfTime];
}

- (void )setUnits:(kUnitsOfTime )units
{
    [self setUnitsElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:units enumType:kEnumTypeUnitsOfTime]]];
}


- (NSNumber *)count
{
    if(self.countElement)
    {
        return [self.countElement value];
    }
    return nil;
}

- (void )setCount:(NSNumber *)count
{
    if(count)
    {
        [self setCountElement:[[FHIRInteger alloc] initWithValue:count]];
    }
    else
    {
        [self setCountElement:nil];
    }
}


- (NSString *)end
{
    if(self.endElement)
    {
        return [self.endElement value];
    }
    return nil;
}

- (void )setEnd:(NSString *)end
{
    if(end)
    {
        [self setEndElement:[[FHIRDateTime alloc] initWithValue:end]];
    }
    else
    {
        [self setEndElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.frequencyElement != nil )
        [result addValidationRange:[self.frequencyElement validate]];
    if(self.whenElement != nil )
        [result addValidationRange:[self.whenElement validate]];
    if(self.durationElement != nil )
        [result addValidationRange:[self.durationElement validate]];
    if(self.unitsElement != nil )
        [result addValidationRange:[self.unitsElement validate]];
    if(self.countElement != nil )
        [result addValidationRange:[self.countElement validate]];
    if(self.endElement != nil )
        [result addValidationRange:[self.endElement validate]];
    
    return result;
}

@end
