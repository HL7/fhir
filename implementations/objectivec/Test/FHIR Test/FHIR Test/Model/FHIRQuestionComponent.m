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
#import "FHIRQuestionComponent.h"

#import "FHIRCodeableConcept.h"
#import "FHIRString.h"
#import "FHIRElement.h"
#import "FHIRCoding.h"
#import "FHIRResourceReference.h"
#import "FHIRGroupComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRQuestionComponent

- (NSString *)text
{
    if(self.textElement)
    {
        return [self.textElement value];
    }
    return nil;
}

- (void )setText:(NSString *)text
{
    if(text)
    {
        [self setTextElement:[[FHIRString alloc] initWithValue:text]];
    }
    else
    {
        [self setTextElement:nil];
    }
}


- (NSString *)remarks
{
    if(self.remarksElement)
    {
        return [self.remarksElement value];
    }
    return nil;
}

- (void )setRemarks:(NSString *)remarks
{
    if(remarks)
    {
        [self setRemarksElement:[[FHIRString alloc] initWithValue:remarks]];
    }
    else
    {
        [self setRemarksElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.name != nil )
        [result addValidationRange:[self.name validate]];
    if(self.textElement != nil )
        [result addValidationRange:[self.textElement validate]];
    if(self.answer != nil )
        [result addValidationRange:[self.answer validate]];
    if(self.choice != nil )
        for(FHIRCoding *elem in self.choice)
            [result addValidationRange:[elem validate]];
    if(self.options != nil )
        [result addValidationRange:[self.options validate]];
    if(self.data != nil )
        [result addValidationRange:[self.data validate]];
    if(self.remarksElement != nil )
        [result addValidationRange:[self.remarksElement validate]];
    if(self.group != nil )
        for(FHIRGroupComponent *elem in self.group)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
