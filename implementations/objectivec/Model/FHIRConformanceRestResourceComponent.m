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
 * null
 */
#import "FHIRConformanceRestResourceComponent.h"

#import "FHIRCode.h"
#import "FHIRResourceReference.h"
#import "FHIRConformanceRestResourceOperationComponent.h"
#import "FHIRBoolean.h"
#import "FHIRString.h"
#import "FHIRConformanceRestResourceSearchParamComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRConformanceRestResourceComponent

- (NSString *)type
{
    if(self.typeElement)
    {
        return [self.typeElement value];
    }
    return nil;
}

- (void )setType:(NSString *)type
{
    if(type)
    {
        [self setTypeElement:[[FHIRCode alloc] initWithValue:type]];
    }
    else
    {
        [self setTypeElement:nil];
    }
}


- (NSNumber *)readHistory
{
    if(self.readHistoryElement)
    {
        return [self.readHistoryElement value];
    }
    return nil;
}

- (void )setReadHistory:(NSNumber *)readHistory
{
    if(readHistory)
    {
        [self setReadHistoryElement:[[FHIRBoolean alloc] initWithValue:readHistory]];
    }
    else
    {
        [self setReadHistoryElement:nil];
    }
}


- (NSNumber *)updateCreate
{
    if(self.updateCreateElement)
    {
        return [self.updateCreateElement value];
    }
    return nil;
}

- (void )setUpdateCreate:(NSNumber *)updateCreate
{
    if(updateCreate)
    {
        [self setUpdateCreateElement:[[FHIRBoolean alloc] initWithValue:updateCreate]];
    }
    else
    {
        [self setUpdateCreateElement:nil];
    }
}


- (NSArray /*<NSString>*/ *)searchInclude
{
    if(self.searchIncludeElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRString *elem in self.searchIncludeElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setSearchInclude:(NSArray /*<NSString>*/ *)searchInclude
{
    if(searchInclude)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in searchInclude)
            [array addObject:[[FHIRString alloc] initWithValue:value]];
        [self setSearchIncludeElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setSearchIncludeElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.typeElement != nil )
        [result addValidationRange:[self.typeElement validate]];
    if(self.profile != nil )
        [result addValidationRange:[self.profile validate]];
    if(self.operation != nil )
        for(FHIRConformanceRestResourceOperationComponent *elem in self.operation)
            [result addValidationRange:[elem validate]];
    if(self.readHistoryElement != nil )
        [result addValidationRange:[self.readHistoryElement validate]];
    if(self.updateCreateElement != nil )
        [result addValidationRange:[self.updateCreateElement validate]];
    if(self.searchIncludeElement != nil )
        for(FHIRString *elem in self.searchIncludeElement)
            [result addValidationRange:[elem validate]];
    if(self.searchParam != nil )
        for(FHIRConformanceRestResourceSearchParamComponent *elem in self.searchParam)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
