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
  

 * Generated on Thu, Jan 30, 2014 05:26+1100 for FHIR v0.12
 */
/*
 * null
 */
#import "FHIREncounterHospitalizationComponent.h"

#import "FHIRIdentifier.h"
#import "FHIRResourceReference.h"
#import "FHIRCodeableConcept.h"
#import "FHIRPeriod.h"
#import "FHIREncounterHospitalizationAccomodationComponent.h"
#import "FHIRBoolean.h"

#import "FHIRErrorList.h"

@implementation FHIREncounterHospitalizationComponent

- (NSNumber *)reAdmission
{
    if(self.reAdmissionElement)
    {
        return [self.reAdmissionElement value];
    }
    return nil;
}

- (void )setReAdmission:(NSNumber *)reAdmission
{
    if(reAdmission)
    {
        [self setReAdmissionElement:[[FHIRBoolean alloc] initWithValue:reAdmission]];
    }
    else
    {
        [self setReAdmissionElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.preAdmissionIdentifier != nil )
        [result addValidationRange:[self.preAdmissionIdentifier validate]];
    if(self.origin != nil )
        [result addValidationRange:[self.origin validate]];
    if(self.admitSource != nil )
        [result addValidationRange:[self.admitSource validate]];
    if(self.period != nil )
        [result addValidationRange:[self.period validate]];
    if(self.accomodation != nil )
        for(FHIREncounterHospitalizationAccomodationComponent *elem in self.accomodation)
            [result addValidationRange:[elem validate]];
    if(self.diet != nil )
        [result addValidationRange:[self.diet validate]];
    if(self.specialCourtesy != nil )
        for(FHIRCodeableConcept *elem in self.specialCourtesy)
            [result addValidationRange:[elem validate]];
    if(self.specialArrangement != nil )
        for(FHIRCodeableConcept *elem in self.specialArrangement)
            [result addValidationRange:[elem validate]];
    if(self.destination != nil )
        [result addValidationRange:[self.destination validate]];
    if(self.dischargeDisposition != nil )
        [result addValidationRange:[self.dischargeDisposition validate]];
    if(self.dischargeDiagnosis != nil )
        [result addValidationRange:[self.dischargeDiagnosis validate]];
    if(self.reAdmissionElement != nil )
        [result addValidationRange:[self.reAdmissionElement validate]];
    
    return result;
}

@end
