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
 * An interaction during which services are provided to the patient
 */
#import "FHIREncounter.h"

#import "FHIRIdentifier.h"
#import "FHIRCode.h"
#import "FHIRCodeableConcept.h"
#import "FHIRResourceReference.h"
#import "FHIREncounterParticipantComponent.h"
#import "FHIRPeriod.h"
#import "FHIRDuration.h"
#import "FHIREncounterHospitalizationComponent.h"
#import "FHIREncounterLocationComponent.h"

#import "FHIRErrorList.h"

@implementation FHIREncounter

- (kEncounterState )status
{
    return [FHIREnumHelper parseString:[self.statusElement value] enumType:kEnumTypeEncounterState];
}

- (void )setStatus:(kEncounterState )status
{
    [self setStatusElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:status enumType:kEnumTypeEncounterState]]];
}


- (kEncounterClass )class_
{
    return [FHIREnumHelper parseString:[self.class_Element value] enumType:kEnumTypeEncounterClass];
}

- (void )setClass_:(kEncounterClass )class_
{
    [self setClass_Element:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:class_ enumType:kEnumTypeEncounterClass]]];
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
    if(self.class_Element != nil )
        [result addValidationRange:[self.class_Element validate]];
    if(self.type != nil )
        for(FHIRCodeableConcept *elem in self.type)
            [result addValidationRange:[elem validate]];
    if(self.subject != nil )
        [result addValidationRange:[self.subject validate]];
    if(self.participant != nil )
        for(FHIREncounterParticipantComponent *elem in self.participant)
            [result addValidationRange:[elem validate]];
    if(self.period != nil )
        [result addValidationRange:[self.period validate]];
    if(self.length != nil )
        [result addValidationRange:[self.length validate]];
    if(self.reason != nil )
        [result addValidationRange:[self.reason validate]];
    if(self.indication != nil )
        [result addValidationRange:[self.indication validate]];
    if(self.priority != nil )
        [result addValidationRange:[self.priority validate]];
    if(self.hospitalization != nil )
        [result addValidationRange:[self.hospitalization validate]];
    if(self.location != nil )
        for(FHIREncounterLocationComponent *elem in self.location)
            [result addValidationRange:[elem validate]];
    if(self.serviceProvider != nil )
        [result addValidationRange:[self.serviceProvider validate]];
    if(self.partOf != nil )
        [result addValidationRange:[self.partOf validate]];
    
    return result;
}

@end
