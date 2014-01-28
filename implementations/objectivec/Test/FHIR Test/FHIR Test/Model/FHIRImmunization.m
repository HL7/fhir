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
 * Immunization event information
 */
#import "FHIRImmunization.h"

#import "FHIRIdentifier.h"
#import "FHIRDateTime.h"
#import "FHIRCodeableConcept.h"
#import "FHIRResourceReference.h"
#import "FHIRBoolean.h"
#import "FHIRString.h"
#import "FHIRDate.h"
#import "FHIRQuantity.h"
#import "FHIRImmunizationExplanationComponent.h"
#import "FHIRImmunizationReactionComponent.h"
#import "FHIRImmunizationVaccinationProtocolComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRImmunization

- (NSString *)date
{
    if(self.dateElement)
    {
        return [self.dateElement value];
    }
    return nil;
}

- (void )setDate:(NSString *)date
{
    if(date)
    {
        [self setDateElement:[[FHIRDateTime alloc] initWithValue:date]];
    }
    else
    {
        [self setDateElement:nil];
    }
}


- (NSNumber *)refusedIndicator
{
    if(self.refusedIndicatorElement)
    {
        return [self.refusedIndicatorElement value];
    }
    return nil;
}

- (void )setRefusedIndicator:(NSNumber *)refusedIndicator
{
    if(refusedIndicator)
    {
        [self setRefusedIndicatorElement:[[FHIRBoolean alloc] initWithValue:refusedIndicator]];
    }
    else
    {
        [self setRefusedIndicatorElement:nil];
    }
}


- (NSNumber *)reported
{
    if(self.reportedElement)
    {
        return [self.reportedElement value];
    }
    return nil;
}

- (void )setReported:(NSNumber *)reported
{
    if(reported)
    {
        [self setReportedElement:[[FHIRBoolean alloc] initWithValue:reported]];
    }
    else
    {
        [self setReportedElement:nil];
    }
}


- (NSString *)lotNumber
{
    if(self.lotNumberElement)
    {
        return [self.lotNumberElement value];
    }
    return nil;
}

- (void )setLotNumber:(NSString *)lotNumber
{
    if(lotNumber)
    {
        [self setLotNumberElement:[[FHIRString alloc] initWithValue:lotNumber]];
    }
    else
    {
        [self setLotNumberElement:nil];
    }
}


- (NSString *)expirationDate
{
    if(self.expirationDateElement)
    {
        return [self.expirationDateElement value];
    }
    return nil;
}

- (void )setExpirationDate:(NSString *)expirationDate
{
    if(expirationDate)
    {
        [self setExpirationDateElement:[[FHIRDate alloc] initWithValue:expirationDate]];
    }
    else
    {
        [self setExpirationDateElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identifier != nil )
        for(FHIRIdentifier *elem in self.identifier)
            [result addValidationRange:[elem validate]];
    if(self.dateElement != nil )
        [result addValidationRange:[self.dateElement validate]];
    if(self.vaccineType != nil )
        [result addValidationRange:[self.vaccineType validate]];
    if(self.subject != nil )
        [result addValidationRange:[self.subject validate]];
    if(self.refusedIndicatorElement != nil )
        [result addValidationRange:[self.refusedIndicatorElement validate]];
    if(self.reportedElement != nil )
        [result addValidationRange:[self.reportedElement validate]];
    if(self.performer != nil )
        [result addValidationRange:[self.performer validate]];
    if(self.requester != nil )
        [result addValidationRange:[self.requester validate]];
    if(self.manufacturer != nil )
        [result addValidationRange:[self.manufacturer validate]];
    if(self.location != nil )
        [result addValidationRange:[self.location validate]];
    if(self.lotNumberElement != nil )
        [result addValidationRange:[self.lotNumberElement validate]];
    if(self.expirationDateElement != nil )
        [result addValidationRange:[self.expirationDateElement validate]];
    if(self.site != nil )
        [result addValidationRange:[self.site validate]];
    if(self.route != nil )
        [result addValidationRange:[self.route validate]];
    if(self.doseQuantity != nil )
        [result addValidationRange:[self.doseQuantity validate]];
    if(self.explanation != nil )
        [result addValidationRange:[self.explanation validate]];
    if(self.reaction != nil )
        for(FHIRImmunizationReactionComponent *elem in self.reaction)
            [result addValidationRange:[elem validate]];
    if(self.vaccinationProtocol != nil )
        for(FHIRImmunizationVaccinationProtocolComponent *elem in self.vaccinationProtocol)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
