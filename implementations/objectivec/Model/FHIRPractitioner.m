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
 * A person with a  formal responsibility in the provisioning of healthcare or related services
 */
#import "FHIRPractitioner.h"

#import "FHIRIdentifier.h"
#import "FHIRHumanName.h"
#import "FHIRContact.h"
#import "FHIRAddress.h"
#import "FHIRCodeableConcept.h"
#import "FHIRDateTime.h"
#import "FHIRAttachment.h"
#import "FHIRResourceReference.h"
#import "FHIRPeriod.h"
#import "FHIRPractitionerQualificationComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRPractitioner

- (NSString *)birthDate
{
    if(self.birthDateElement)
    {
        return [self.birthDateElement value];
    }
    return nil;
}

- (void )setBirthDate:(NSString *)birthDate
{
    if(birthDate)
    {
        [self setBirthDateElement:[[FHIRDateTime alloc] initWithValue:birthDate]];
    }
    else
    {
        [self setBirthDateElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identifier != nil )
        for(FHIRIdentifier *elem in self.identifier)
            [result addValidationRange:[elem validate]];
    if(self.name != nil )
        [result addValidationRange:[self.name validate]];
    if(self.telecom != nil )
        for(FHIRContact *elem in self.telecom)
            [result addValidationRange:[elem validate]];
    if(self.address != nil )
        [result addValidationRange:[self.address validate]];
    if(self.gender != nil )
        [result addValidationRange:[self.gender validate]];
    if(self.birthDateElement != nil )
        [result addValidationRange:[self.birthDateElement validate]];
    if(self.photo != nil )
        for(FHIRAttachment *elem in self.photo)
            [result addValidationRange:[elem validate]];
    if(self.organization != nil )
        [result addValidationRange:[self.organization validate]];
    if(self.role != nil )
        for(FHIRCodeableConcept *elem in self.role)
            [result addValidationRange:[elem validate]];
    if(self.specialty != nil )
        for(FHIRCodeableConcept *elem in self.specialty)
            [result addValidationRange:[elem validate]];
    if(self.period != nil )
        [result addValidationRange:[self.period validate]];
    if(self.location != nil )
        for(FHIRResourceReference *elem in self.location)
            [result addValidationRange:[elem validate]];
    if(self.qualification != nil )
        for(FHIRPractitionerQualificationComponent *elem in self.qualification)
            [result addValidationRange:[elem validate]];
    if(self.communication != nil )
        for(FHIRCodeableConcept *elem in self.communication)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
