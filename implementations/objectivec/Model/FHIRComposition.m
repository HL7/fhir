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
 * A set of resources composed into a single coherent clinical statement with clinical attestation
 */
#import "FHIRComposition.h"

#import "FHIRIdentifier.h"
#import "FHIRInstant.h"
#import "FHIRCodeableConcept.h"
#import "FHIRString.h"
#import "FHIRCode.h"
#import "FHIRCoding.h"
#import "FHIRResourceReference.h"
#import "FHIRCompositionAttesterComponent.h"
#import "FHIRCompositionEventComponent.h"
#import "FHIRSectionComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRComposition

- (NSDate *)instant
{
    if(self.instantElement)
    {
        return [self.instantElement value];
    }
    return nil;
}

- (void )setInstant:(NSDate *)instant
{
    if(instant)
    {
        [self setInstantElement:[[FHIRInstant alloc] initWithValue:instant]];
    }
    else
    {
        [self setInstantElement:nil];
    }
}


- (NSString *)title
{
    if(self.titleElement)
    {
        return [self.titleElement value];
    }
    return nil;
}

- (void )setTitle:(NSString *)title
{
    if(title)
    {
        [self setTitleElement:[[FHIRString alloc] initWithValue:title]];
    }
    else
    {
        [self setTitleElement:nil];
    }
}


- (kCompositionStatus )status
{
    return [FHIREnumHelper parseString:[self.statusElement value] enumType:kEnumTypeCompositionStatus];
}

- (void )setStatus:(kCompositionStatus )status
{
    [self setStatusElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:status enumType:kEnumTypeCompositionStatus]]];
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identifier != nil )
        [result addValidationRange:[self.identifier validate]];
    if(self.instantElement != nil )
        [result addValidationRange:[self.instantElement validate]];
    if(self.type != nil )
        [result addValidationRange:[self.type validate]];
    if(self.class_ != nil )
        [result addValidationRange:[self.class_ validate]];
    if(self.titleElement != nil )
        [result addValidationRange:[self.titleElement validate]];
    if(self.statusElement != nil )
        [result addValidationRange:[self.statusElement validate]];
    if(self.confidentiality != nil )
        [result addValidationRange:[self.confidentiality validate]];
    if(self.subject != nil )
        [result addValidationRange:[self.subject validate]];
    if(self.author != nil )
        for(FHIRResourceReference *elem in self.author)
            [result addValidationRange:[elem validate]];
    if(self.attester != nil )
        for(FHIRCompositionAttesterComponent *elem in self.attester)
            [result addValidationRange:[elem validate]];
    if(self.custodian != nil )
        [result addValidationRange:[self.custodian validate]];
    if(self.event_ != nil )
        [result addValidationRange:[self.event_ validate]];
    if(self.encounter != nil )
        [result addValidationRange:[self.encounter validate]];
    if(self.section != nil )
        for(FHIRSectionComponent *elem in self.section)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
