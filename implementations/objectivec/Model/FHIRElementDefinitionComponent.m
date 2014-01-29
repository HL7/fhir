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
#import "FHIRElementDefinitionComponent.h"

#import "FHIRString.h"
#import "FHIRInteger.h"
#import "FHIRTypeRefComponent.h"
#import "FHIRElement.h"
#import "FHIRId.h"
#import "FHIRElementDefinitionConstraintComponent.h"
#import "FHIRBoolean.h"
#import "FHIRElementDefinitionBindingComponent.h"
#import "FHIRElementDefinitionMappingComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRElementDefinitionComponent

- (NSString *)short_
{
    if(self.short_Element)
    {
        return [self.short_Element value];
    }
    return nil;
}

- (void )setShort_:(NSString *)short_
{
    if(short_)
    {
        [self setShort_Element:[[FHIRString alloc] initWithValue:short_]];
    }
    else
    {
        [self setShort_Element:nil];
    }
}


- (NSString *)formal
{
    if(self.formalElement)
    {
        return [self.formalElement value];
    }
    return nil;
}

- (void )setFormal:(NSString *)formal
{
    if(formal)
    {
        [self setFormalElement:[[FHIRString alloc] initWithValue:formal]];
    }
    else
    {
        [self setFormalElement:nil];
    }
}


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


- (NSString *)requirements
{
    if(self.requirementsElement)
    {
        return [self.requirementsElement value];
    }
    return nil;
}

- (void )setRequirements:(NSString *)requirements
{
    if(requirements)
    {
        [self setRequirementsElement:[[FHIRString alloc] initWithValue:requirements]];
    }
    else
    {
        [self setRequirementsElement:nil];
    }
}


- (NSArray /*<NSString>*/ *)synonym
{
    if(self.synonymElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRString *elem in self.synonymElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setSynonym:(NSArray /*<NSString>*/ *)synonym
{
    if(synonym)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in synonym)
            [array addObject:[[FHIRString alloc] initWithValue:value]];
        [self setSynonymElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setSynonymElement:nil];
    }
}


- (NSNumber *)min
{
    if(self.minElement)
    {
        return [self.minElement value];
    }
    return nil;
}

- (void )setMin:(NSNumber *)min
{
    if(min)
    {
        [self setMinElement:[[FHIRInteger alloc] initWithValue:min]];
    }
    else
    {
        [self setMinElement:nil];
    }
}


- (NSString *)max
{
    if(self.maxElement)
    {
        return [self.maxElement value];
    }
    return nil;
}

- (void )setMax:(NSString *)max
{
    if(max)
    {
        [self setMaxElement:[[FHIRString alloc] initWithValue:max]];
    }
    else
    {
        [self setMaxElement:nil];
    }
}


- (NSString *)nameReference
{
    if(self.nameReferenceElement)
    {
        return [self.nameReferenceElement value];
    }
    return nil;
}

- (void )setNameReference:(NSString *)nameReference
{
    if(nameReference)
    {
        [self setNameReferenceElement:[[FHIRString alloc] initWithValue:nameReference]];
    }
    else
    {
        [self setNameReferenceElement:nil];
    }
}


- (NSNumber *)maxLength
{
    if(self.maxLengthElement)
    {
        return [self.maxLengthElement value];
    }
    return nil;
}

- (void )setMaxLength:(NSNumber *)maxLength
{
    if(maxLength)
    {
        [self setMaxLengthElement:[[FHIRInteger alloc] initWithValue:maxLength]];
    }
    else
    {
        [self setMaxLengthElement:nil];
    }
}


- (NSArray /*<NSString>*/ *)condition
{
    if(self.conditionElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRId *elem in self.conditionElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setCondition:(NSArray /*<NSString>*/ *)condition
{
    if(condition)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in condition)
            [array addObject:[[FHIRId alloc] initWithValue:value]];
        [self setConditionElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setConditionElement:nil];
    }
}


- (NSNumber *)mustSupport
{
    if(self.mustSupportElement)
    {
        return [self.mustSupportElement value];
    }
    return nil;
}

- (void )setMustSupport:(NSNumber *)mustSupport
{
    if(mustSupport)
    {
        [self setMustSupportElement:[[FHIRBoolean alloc] initWithValue:mustSupport]];
    }
    else
    {
        [self setMustSupportElement:nil];
    }
}


- (NSNumber *)isModifier
{
    if(self.isModifierElement)
    {
        return [self.isModifierElement value];
    }
    return nil;
}

- (void )setIsModifier:(NSNumber *)isModifier
{
    if(isModifier)
    {
        [self setIsModifierElement:[[FHIRBoolean alloc] initWithValue:isModifier]];
    }
    else
    {
        [self setIsModifierElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.short_Element != nil )
        [result addValidationRange:[self.short_Element validate]];
    if(self.formalElement != nil )
        [result addValidationRange:[self.formalElement validate]];
    if(self.commentsElement != nil )
        [result addValidationRange:[self.commentsElement validate]];
    if(self.requirementsElement != nil )
        [result addValidationRange:[self.requirementsElement validate]];
    if(self.synonymElement != nil )
        for(FHIRString *elem in self.synonymElement)
            [result addValidationRange:[elem validate]];
    if(self.minElement != nil )
        [result addValidationRange:[self.minElement validate]];
    if(self.maxElement != nil )
        [result addValidationRange:[self.maxElement validate]];
    if(self.type != nil )
        for(FHIRTypeRefComponent *elem in self.type)
            [result addValidationRange:[elem validate]];
    if(self.nameReferenceElement != nil )
        [result addValidationRange:[self.nameReferenceElement validate]];
    if(self.value != nil )
        [result addValidationRange:[self.value validate]];
    if(self.example != nil )
        [result addValidationRange:[self.example validate]];
    if(self.maxLengthElement != nil )
        [result addValidationRange:[self.maxLengthElement validate]];
    if(self.conditionElement != nil )
        for(FHIRId *elem in self.conditionElement)
            [result addValidationRange:[elem validate]];
    if(self.constraint != nil )
        for(FHIRElementDefinitionConstraintComponent *elem in self.constraint)
            [result addValidationRange:[elem validate]];
    if(self.mustSupportElement != nil )
        [result addValidationRange:[self.mustSupportElement validate]];
    if(self.isModifierElement != nil )
        [result addValidationRange:[self.isModifierElement validate]];
    if(self.binding != nil )
        [result addValidationRange:[self.binding validate]];
    if(self.mapping != nil )
        for(FHIRElementDefinitionMappingComponent *elem in self.mapping)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
