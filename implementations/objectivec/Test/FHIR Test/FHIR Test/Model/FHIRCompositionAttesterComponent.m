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
#import "FHIRCompositionAttesterComponent.h"

#import "FHIRCode.h"
#import "FHIRDateTime.h"
#import "FHIRResourceReference.h"

#import "FHIRErrorList.h"

@implementation FHIRCompositionAttesterComponent

- (NSArray /*<kCompositionAttestationMode>*/ *)mode
{
    if(self.modeElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRCode *elem in self.modeElement)
            [array addObject:[NSNumber numberWithInt:[FHIREnumHelper parseString:[elem value] enumType:kEnumTypeCompositionAttestationMode]]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setMode:(NSArray /*<kCompositionAttestationMode>*/ *)mode
{
    if(mode)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSNumber *value in self.mode)
            [array addObject:[FHIREnumHelper enumToString:[value intValue] enumType:kEnumTypeCompositionAttestationMode]];
        [self setModeElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setModeElement:nil];
    }
}


- (NSString *)time
{
    if(self.timeElement)
    {
        return [self.timeElement value];
    }
    return nil;
}

- (void )setTime:(NSString *)time
{
    if(time)
    {
        [self setTimeElement:[[FHIRDateTime alloc] initWithValue:time]];
    }
    else
    {
        [self setTimeElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.modeElement != nil )
        for(FHIRCode *elem in self.modeElement)
            [result addValidationRange:[elem validate]];
    if(self.timeElement != nil )
        [result addValidationRange:[self.timeElement validate]];
    if(self.party != nil )
        [result addValidationRange:[self.party validate]];
    
    return result;
}

@end
