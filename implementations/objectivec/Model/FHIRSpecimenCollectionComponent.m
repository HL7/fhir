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
#import "FHIRSpecimenCollectionComponent.h"

#import "FHIRResourceReference.h"
#import "FHIRString.h"
#import "FHIRElement.h"
#import "FHIRQuantity.h"
#import "FHIRCodeableConcept.h"

#import "FHIRErrorList.h"

@implementation FHIRSpecimenCollectionComponent

- (NSArray /*<NSString>*/ *)comment
{
    if(self.commentElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRString *elem in self.commentElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setComment:(NSArray /*<NSString>*/ *)comment
{
    if(comment)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in comment)
            [array addObject:[[FHIRString alloc] initWithValue:value]];
        [self setCommentElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setCommentElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.collector != nil )
        [result addValidationRange:[self.collector validate]];
    if(self.commentElement != nil )
        for(FHIRString *elem in self.commentElement)
            [result addValidationRange:[elem validate]];
    if(self.collected != nil )
        [result addValidationRange:[self.collected validate]];
    if(self.quantity != nil )
        [result addValidationRange:[self.quantity validate]];
    if(self.method != nil )
        [result addValidationRange:[self.method validate]];
    if(self.sourceSite != nil )
        [result addValidationRange:[self.sourceSite validate]];
    
    return result;
}

@end
