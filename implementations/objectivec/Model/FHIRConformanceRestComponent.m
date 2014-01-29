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
#import "FHIRConformanceRestComponent.h"

#import "FHIRCode.h"
#import "FHIRString.h"
#import "FHIRConformanceRestSecurityComponent.h"
#import "FHIRConformanceRestResourceComponent.h"
#import "FHIRConformanceRestOperationComponent.h"
#import "FHIRConformanceRestQueryComponent.h"
#import "FHIRUri.h"

#import "FHIRErrorList.h"

@implementation FHIRConformanceRestComponent

- (kRestfulConformanceMode )mode
{
    return [FHIREnumHelper parseString:[self.modeElement value] enumType:kEnumTypeRestfulConformanceMode];
}

- (void )setMode:(kRestfulConformanceMode )mode
{
    [self setModeElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:mode enumType:kEnumTypeRestfulConformanceMode]]];
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


- (NSArray /*<NSString>*/ *)documentMailbox
{
    if(self.documentMailboxElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRUri *elem in self.documentMailboxElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setDocumentMailbox:(NSArray /*<NSString>*/ *)documentMailbox
{
    if(documentMailbox)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in documentMailbox)
            [array addObject:[[FHIRUri alloc] initWithValue:value]];
        [self setDocumentMailboxElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setDocumentMailboxElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.modeElement != nil )
        [result addValidationRange:[self.modeElement validate]];
    if(self.documentationElement != nil )
        [result addValidationRange:[self.documentationElement validate]];
    if(self.security != nil )
        [result addValidationRange:[self.security validate]];
    if(self.resource != nil )
        for(FHIRConformanceRestResourceComponent *elem in self.resource)
            [result addValidationRange:[elem validate]];
    if(self.operation != nil )
        for(FHIRConformanceRestOperationComponent *elem in self.operation)
            [result addValidationRange:[elem validate]];
    if(self.query != nil )
        for(FHIRConformanceRestQueryComponent *elem in self.query)
            [result addValidationRange:[elem validate]];
    if(self.documentMailboxElement != nil )
        for(FHIRUri *elem in self.documentMailboxElement)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
