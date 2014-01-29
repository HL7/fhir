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
 * Dispensing a medication to a named patient
 */
#import "FHIRMedicationDispense.h"

#import "FHIRIdentifier.h"
#import "FHIRCode.h"
#import "FHIRResourceReference.h"
#import "FHIRMedicationDispenseDispenseComponent.h"
#import "FHIRMedicationDispenseSubstitutionComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRMedicationDispense

- (kMedicationDispenseStatus )status
{
    return [FHIREnumHelper parseString:[self.statusElement value] enumType:kEnumTypeMedicationDispenseStatus];
}

- (void )setStatus:(kMedicationDispenseStatus )status
{
    [self setStatusElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:status enumType:kEnumTypeMedicationDispenseStatus]]];
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identifier != nil )
        [result addValidationRange:[self.identifier validate]];
    if(self.statusElement != nil )
        [result addValidationRange:[self.statusElement validate]];
    if(self.patient != nil )
        [result addValidationRange:[self.patient validate]];
    if(self.dispenser != nil )
        [result addValidationRange:[self.dispenser validate]];
    if(self.authorizingPrescription != nil )
        for(FHIRResourceReference *elem in self.authorizingPrescription)
            [result addValidationRange:[elem validate]];
    if(self.dispense != nil )
        for(FHIRMedicationDispenseDispenseComponent *elem in self.dispense)
            [result addValidationRange:[elem validate]];
    if(self.substitution != nil )
        [result addValidationRange:[self.substitution validate]];
    
    return result;
}

@end
