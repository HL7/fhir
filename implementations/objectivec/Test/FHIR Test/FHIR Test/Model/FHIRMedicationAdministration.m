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
 * Administration of medication to a patient
 */
#import "FHIRMedicationAdministration.h"

#import "FHIRIdentifier.h"
#import "FHIRCode.h"
#import "FHIRResourceReference.h"
#import "FHIRBoolean.h"
#import "FHIRCodeableConcept.h"
#import "FHIRPeriod.h"
#import "FHIRMedicationAdministrationDosageComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRMedicationAdministration

- (kMedicationAdministrationStatus )status
{
    return [FHIREnumHelper parseString:[self.statusElement value] enumType:kEnumTypeMedicationAdministrationStatus];
}

- (void )setStatus:(kMedicationAdministrationStatus )status
{
    [self setStatusElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:status enumType:kEnumTypeMedicationAdministrationStatus]]];
}


- (NSNumber *)wasNotGiven
{
    if(self.wasNotGivenElement)
    {
        return [self.wasNotGivenElement value];
    }
    return nil;
}

- (void )setWasNotGiven:(NSNumber *)wasNotGiven
{
    if(wasNotGiven)
    {
        [self setWasNotGivenElement:[[FHIRBoolean alloc] initWithValue:wasNotGiven]];
    }
    else
    {
        [self setWasNotGivenElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identifier != nil )
        for(FHIRIdentifier *elem in self.identifier)
            [result addValidationRange:[elem validate]];
    if(self.statusElement != nil )
        [result addValidationRange:[self.statusElement validate]];
    if(self.patient != nil )
        [result addValidationRange:[self.patient validate]];
    if(self.practitioner != nil )
        [result addValidationRange:[self.practitioner validate]];
    if(self.encounter != nil )
        [result addValidationRange:[self.encounter validate]];
    if(self.prescription != nil )
        [result addValidationRange:[self.prescription validate]];
    if(self.wasNotGivenElement != nil )
        [result addValidationRange:[self.wasNotGivenElement validate]];
    if(self.reasonNotGiven != nil )
        for(FHIRCodeableConcept *elem in self.reasonNotGiven)
            [result addValidationRange:[elem validate]];
    if(self.whenGiven != nil )
        [result addValidationRange:[self.whenGiven validate]];
    if(self.medication != nil )
        [result addValidationRange:[self.medication validate]];
    if(self.device != nil )
        for(FHIRResourceReference *elem in self.device)
            [result addValidationRange:[elem validate]];
    if(self.dosage != nil )
        for(FHIRMedicationAdministrationDosageComponent *elem in self.dosage)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
