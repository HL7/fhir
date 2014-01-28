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
 * Simple observations
 */
#import "FHIRObservation.h"

#import "FHIRCodeableConcept.h"
#import "FHIRElement.h"
#import "FHIRString.h"
#import "FHIRInstant.h"
#import "FHIRCode.h"
#import "FHIRIdentifier.h"
#import "FHIRResourceReference.h"
#import "FHIRObservationReferenceRangeComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRObservation

- (NSString *)comments
{
    if(self.commentsElement)
    {
        return [self.commentsElement value];
    }
    return nil;
}

- (void )setComments:(NSString *)comments
{
    if(comments)
    {
        [self setCommentsElement:[[FHIRString alloc] initWithValue:comments]];
    }
    else
    {
        [self setCommentsElement:nil];
    }
}


- (NSDate *)issued
{
    if(self.issuedElement)
    {
        return [self.issuedElement value];
    }
    return nil;
}

- (void )setIssued:(NSDate *)issued
{
    if(issued)
    {
        [self setIssuedElement:[[FHIRInstant alloc] initWithValue:issued]];
    }
    else
    {
        [self setIssuedElement:nil];
    }
}


- (kObservationStatus )status
{
    return [FHIREnumHelper parseString:[self.statusElement value] enumType:kEnumTypeObservationStatus];
}

- (void )setStatus:(kObservationStatus )status
{
    [self setStatusElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:status enumType:kEnumTypeObservationStatus]]];
}


- (kObservationReliability )reliability
{
    return [FHIREnumHelper parseString:[self.reliabilityElement value] enumType:kEnumTypeObservationReliability];
}

- (void )setReliability:(kObservationReliability )reliability
{
    [self setReliabilityElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:reliability enumType:kEnumTypeObservationReliability]]];
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.name != nil )
        [result addValidationRange:[self.name validate]];
    if(self.value != nil )
        [result addValidationRange:[self.value validate]];
    if(self.interpretation != nil )
        [result addValidationRange:[self.interpretation validate]];
    if(self.commentsElement != nil )
        [result addValidationRange:[self.commentsElement validate]];
    if(self.applies != nil )
        [result addValidationRange:[self.applies validate]];
    if(self.issuedElement != nil )
        [result addValidationRange:[self.issuedElement validate]];
    if(self.statusElement != nil )
        [result addValidationRange:[self.statusElement validate]];
    if(self.reliabilityElement != nil )
        [result addValidationRange:[self.reliabilityElement validate]];
    if(self.bodySite != nil )
        [result addValidationRange:[self.bodySite validate]];
    if(self.method != nil )
        [result addValidationRange:[self.method validate]];
    if(self.identifier != nil )
        [result addValidationRange:[self.identifier validate]];
    if(self.subject != nil )
        [result addValidationRange:[self.subject validate]];
    if(self.specimen != nil )
        [result addValidationRange:[self.specimen validate]];
    if(self.performer != nil )
        for(FHIRResourceReference *elem in self.performer)
            [result addValidationRange:[elem validate]];
    if(self.referenceRange != nil )
        for(FHIRObservationReferenceRangeComponent *elem in self.referenceRange)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
