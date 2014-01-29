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
 * Group of multiple entities
 */
#import "FHIRGroup.h"

#import "FHIRIdentifier.h"
#import "FHIRCode.h"
#import "FHIRBoolean.h"
#import "FHIRCodeableConcept.h"
#import "FHIRString.h"
#import "FHIRInteger.h"
#import "FHIRGroupCharacteristicComponent.h"
#import "FHIRResourceReference.h"

#import "FHIRErrorList.h"

@implementation FHIRGroup

- (kGroupType )type
{
    return [FHIREnumHelper parseString:[self.typeElement value] enumType:kEnumTypeGroupType];
}

- (void )setType:(kGroupType )type
{
    [self setTypeElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:type enumType:kEnumTypeGroupType]]];
}


- (NSNumber *)actual
{
    if(self.actualElement)
    {
        return [self.actualElement value];
    }
    return nil;
}

- (void )setActual:(NSNumber *)actual
{
    if(actual)
    {
        [self setActualElement:[[FHIRBoolean alloc] initWithValue:actual]];
    }
    else
    {
        [self setActualElement:nil];
    }
}


- (NSString *)name
{
    if(self.nameElement)
    {
        return [self.nameElement value];
    }
    return nil;
}

- (void )setName:(NSString *)name
{
    if(name)
    {
        [self setNameElement:[[FHIRString alloc] initWithValue:name]];
    }
    else
    {
        [self setNameElement:nil];
    }
}


- (NSNumber *)quantity
{
    if(self.quantityElement)
    {
        return [self.quantityElement value];
    }
    return nil;
}

- (void )setQuantity:(NSNumber *)quantity
{
    if(quantity)
    {
        [self setQuantityElement:[[FHIRInteger alloc] initWithValue:quantity]];
    }
    else
    {
        [self setQuantityElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identifier != nil )
        [result addValidationRange:[self.identifier validate]];
    if(self.typeElement != nil )
        [result addValidationRange:[self.typeElement validate]];
    if(self.actualElement != nil )
        [result addValidationRange:[self.actualElement validate]];
    if(self.code != nil )
        [result addValidationRange:[self.code validate]];
    if(self.nameElement != nil )
        [result addValidationRange:[self.nameElement validate]];
    if(self.quantityElement != nil )
        [result addValidationRange:[self.quantityElement validate]];
    if(self.characteristic != nil )
        for(FHIRGroupCharacteristicComponent *elem in self.characteristic)
            [result addValidationRange:[elem validate]];
    if(self.member != nil )
        for(FHIRResourceReference *elem in self.member)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
