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
#import "FHIRConformanceMessagingEventComponent.h"

#import "FHIRCoding.h"
#import "FHIRCode.h"
#import "FHIRResourceReference.h"
#import "FHIRString.h"

#import "FHIRErrorList.h"

@implementation FHIRConformanceMessagingEventComponent

- (kMessageSignificanceCategory )category
{
    return [FHIREnumHelper parseString:[self.categoryElement value] enumType:kEnumTypeMessageSignificanceCategory];
}

- (void )setCategory:(kMessageSignificanceCategory )category
{
    [self setCategoryElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:category enumType:kEnumTypeMessageSignificanceCategory]]];
}


- (kConformanceEventMode )mode
{
    return [FHIREnumHelper parseString:[self.modeElement value] enumType:kEnumTypeConformanceEventMode];
}

- (void )setMode:(kConformanceEventMode )mode
{
    [self setModeElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:mode enumType:kEnumTypeConformanceEventMode]]];
}


- (NSString *)focus
{
    if(self.focusElement)
    {
        return [self.focusElement value];
    }
    return nil;
}

- (void )setFocus:(NSString *)focus
{
    if(focus)
    {
        [self setFocusElement:[[FHIRCode alloc] initWithValue:focus]];
    }
    else
    {
        [self setFocusElement:nil];
    }
}


- (NSString *)documentation
{
    if(self.documentationElement)
    {
        return [self.documentationElement value];
    }
    return nil;
}

- (void )setDocumentation:(NSString *)documentation
{
    if(documentation)
    {
        [self setDocumentationElement:[[FHIRString alloc] initWithValue:documentation]];
    }
    else
    {
        [self setDocumentationElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.code != nil )
        [result addValidationRange:[self.code validate]];
    if(self.categoryElement != nil )
        [result addValidationRange:[self.categoryElement validate]];
    if(self.modeElement != nil )
        [result addValidationRange:[self.modeElement validate]];
    if(self.protocol != nil )
        for(FHIRCoding *elem in self.protocol)
            [result addValidationRange:[elem validate]];
    if(self.focusElement != nil )
        [result addValidationRange:[self.focusElement validate]];
    if(self.request != nil )
        [result addValidationRange:[self.request validate]];
    if(self.response != nil )
        [result addValidationRange:[self.response validate]];
    if(self.documentationElement != nil )
        [result addValidationRange:[self.documentationElement validate]];
    
    return result;
}

@end
