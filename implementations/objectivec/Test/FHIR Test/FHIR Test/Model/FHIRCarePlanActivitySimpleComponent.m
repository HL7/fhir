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
 * null
 */
#import "FHIRCarePlanActivitySimpleComponent.h"

#import "FHIRCode.h"
#import "FHIRCodeableConcept.h"
#import "FHIRElement.h"
#import "FHIRResourceReference.h"
#import "FHIRQuantity.h"
#import "FHIRString.h"

#import "FHIRErrorList.h"

@implementation FHIRCarePlanActivitySimpleComponent

- (kCarePlanActivityCategory )category
{
    return [FHIREnumHelper parseString:[self.categoryElement value] enumType:kEnumTypeCarePlanActivityCategory];
}

- (void )setCategory:(kCarePlanActivityCategory )category
{
    [self setCategoryElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:category enumType:kEnumTypeCarePlanActivityCategory]]];
}


- (NSString *)details
{
    if(self.detailsElement)
    {
        return [self.detailsElement value];
    }
    return nil;
}

- (void )setDetails:(NSString *)details
{
    if(details)
    {
        [self setDetailsElement:[[FHIRString alloc] initWithValue:details]];
    }
    else
    {
        [self setDetailsElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.categoryElement != nil )
        [result addValidationRange:[self.categoryElement validate]];
    if(self.code != nil )
        [result addValidationRange:[self.code validate]];
    if(self.timing != nil )
        [result addValidationRange:[self.timing validate]];
    if(self.location != nil )
        [result addValidationRange:[self.location validate]];
    if(self.performer != nil )
        for(FHIRResourceReference *elem in self.performer)
            [result addValidationRange:[elem validate]];
    if(self.product != nil )
        [result addValidationRange:[self.product validate]];
    if(self.dailyAmount != nil )
        [result addValidationRange:[self.dailyAmount validate]];
    if(self.quantity != nil )
        [result addValidationRange:[self.quantity validate]];
    if(self.detailsElement != nil )
        [result addValidationRange:[self.detailsElement validate]];
    
    return result;
}

@end
