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
#import "FHIRImmunizationRecommendationRecommendationProtocolComponent.h"

#import "FHIRInteger.h"
#import "FHIRString.h"
#import "FHIRResourceReference.h"

#import "FHIRErrorList.h"

@implementation FHIRImmunizationRecommendationRecommendationProtocolComponent

- (NSNumber *)doseSequence
{
    if(self.doseSequenceElement)
    {
        return [self.doseSequenceElement value];
    }
    return nil;
}

- (void )setDoseSequence:(NSNumber *)doseSequence
{
    if(doseSequence)
    {
        [self setDoseSequenceElement:[[FHIRInteger alloc] initWithValue:doseSequence]];
    }
    else
    {
        [self setDoseSequenceElement:nil];
    }
}


- (NSString *)description
{
    if(self.descriptionElement)
    {
        return [self.descriptionElement value];
    }
    return nil;
}

- (void )setDescription:(NSString *)description
{
    if(description)
    {
        [self setDescriptionElement:[[FHIRString alloc] initWithValue:description]];
    }
    else
    {
        [self setDescriptionElement:nil];
    }
}


- (NSString *)series
{
    if(self.seriesElement)
    {
        return [self.seriesElement value];
    }
    return nil;
}

- (void )setSeries:(NSString *)series
{
    if(series)
    {
        [self setSeriesElement:[[FHIRString alloc] initWithValue:series]];
    }
    else
    {
        [self setSeriesElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.doseSequenceElement != nil )
        [result addValidationRange:[self.doseSequenceElement validate]];
    if(self.descriptionElement != nil )
        [result addValidationRange:[self.descriptionElement validate]];
    if(self.authority != nil )
        [result addValidationRange:[self.authority validate]];
    if(self.seriesElement != nil )
        [result addValidationRange:[self.seriesElement validate]];
    
    return result;
}

@end
