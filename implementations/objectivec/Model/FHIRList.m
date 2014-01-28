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
 * Information summarized from a list of other resources
 */
#import "FHIRList.h"

#import "FHIRIdentifier.h"
#import "FHIRCodeableConcept.h"
#import "FHIRResourceReference.h"
#import "FHIRDateTime.h"
#import "FHIRBoolean.h"
#import "FHIRCode.h"
#import "FHIRListEntryComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRList

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


- (NSNumber *)ordered
{
    if(self.orderedElement)
    {
        return [self.orderedElement value];
    }
    return nil;
}

- (void )setOrdered:(NSNumber *)ordered
{
    if(ordered)
    {
        [self setOrderedElement:[[FHIRBoolean alloc] initWithValue:ordered]];
    }
    else
    {
        [self setOrderedElement:nil];
    }
}


- (kListMode )mode
{
    return [FHIREnumHelper parseString:[self.modeElement value] enumType:kEnumTypeListMode];
}

- (void )setMode:(kListMode )mode
{
    [self setModeElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:mode enumType:kEnumTypeListMode]]];
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identifier != nil )
        for(FHIRIdentifier *elem in self.identifier)
            [result addValidationRange:[elem validate]];
    if(self.code != nil )
        [result addValidationRange:[self.code validate]];
    if(self.subject != nil )
        [result addValidationRange:[self.subject validate]];
    if(self.source != nil )
        [result addValidationRange:[self.source validate]];
    if(self.dateElement != nil )
        [result addValidationRange:[self.dateElement validate]];
    if(self.orderedElement != nil )
        [result addValidationRange:[self.orderedElement validate]];
    if(self.modeElement != nil )
        [result addValidationRange:[self.modeElement validate]];
    if(self.entry != nil )
        for(FHIRListEntryComponent *elem in self.entry)
            [result addValidationRange:[elem validate]];
    if(self.emptyReason != nil )
        [result addValidationRange:[self.emptyReason validate]];
    
    return result;
}

@end
