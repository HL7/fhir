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
 * A reference to a document
 */
#import "FHIRDocumentReference.h"

#import "FHIRIdentifier.h"
#import "FHIRResourceReference.h"
#import "FHIRCodeableConcept.h"
#import "FHIRUri.h"
#import "FHIRDateTime.h"
#import "FHIRInstant.h"
#import "FHIRCode.h"
#import "FHIRDocumentReferenceRelatesToComponent.h"
#import "FHIRString.h"
#import "FHIRInteger.h"
#import "FHIRDocumentReferenceServiceComponent.h"
#import "FHIRDocumentReferenceContextComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRDocumentReference

- (NSString *)policyManager
{
    if(self.policyManagerElement)
    {
        return [self.policyManagerElement value];
    }
    return nil;
}

- (void )setPolicyManager:(NSString *)policyManager
{
    if(policyManager)
    {
        [self setPolicyManagerElement:[[FHIRUri alloc] initWithValue:policyManager]];
    }
    else
    {
        [self setPolicyManagerElement:nil];
    }
}


- (NSString *)created
{
    if(self.createdElement)
    {
        return [self.createdElement value];
    }
    return nil;
}

- (void )setCreated:(NSString *)created
{
    if(created)
    {
        [self setCreatedElement:[[FHIRDateTime alloc] initWithValue:created]];
    }
    else
    {
        [self setCreatedElement:nil];
    }
}


- (NSDate *)indexed
{
    if(self.indexedElement)
    {
        return [self.indexedElement value];
    }
    return nil;
}

- (void )setIndexed:(NSDate *)indexed
{
    if(indexed)
    {
        [self setIndexedElement:[[FHIRInstant alloc] initWithValue:indexed]];
    }
    else
    {
        [self setIndexedElement:nil];
    }
}


- (kDocumentReferenceStatus )status
{
    return [FHIREnumHelper parseString:[self.statusElement value] enumType:kEnumTypeDocumentReferenceStatus];
}

- (void )setStatus:(kDocumentReferenceStatus )status
{
    [self setStatusElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:status enumType:kEnumTypeDocumentReferenceStatus]]];
}


- (NSString *)description
{
    if(self.descriptionElement)
    {
        return [self.descriptionElement value];
    }
    return nil;
}

- (void )setDescription:(NSString *)description
{
    if(description)
    {
        [self setDescriptionElement:[[FHIRString alloc] initWithValue:description]];
    }
    else
    {
        [self setDescriptionElement:nil];
    }
}


- (NSString *)primaryLanguage
{
    if(self.primaryLanguageElement)
    {
        return [self.primaryLanguageElement value];
    }
    return nil;
}

- (void )setPrimaryLanguage:(NSString *)primaryLanguage
{
    if(primaryLanguage)
    {
        [self setPrimaryLanguageElement:[[FHIRCode alloc] initWithValue:primaryLanguage]];
    }
    else
    {
        [self setPrimaryLanguageElement:nil];
    }
}


- (NSString *)mimeType
{
    if(self.mimeTypeElement)
    {
        return [self.mimeTypeElement value];
    }
    return nil;
}

- (void )setMimeType:(NSString *)mimeType
{
    if(mimeType)
    {
        [self setMimeTypeElement:[[FHIRCode alloc] initWithValue:mimeType]];
    }
    else
    {
        [self setMimeTypeElement:nil];
    }
}


- (NSArray /*<NSString>*/ *)format
{
    if(self.formatElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRUri *elem in self.formatElement)
            [array addObject:[elem value]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setFormat:(NSArray /*<NSString>*/ *)format
{
    if(format)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSString *value in format)
            [array addObject:[[FHIRUri alloc] initWithValue:value]];
        [self setFormatElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setFormatElement:nil];
    }
}


- (NSNumber *)size
{
    if(self.sizeElement)
    {
        return [self.sizeElement value];
    }
    return nil;
}

- (void )setSize:(NSNumber *)size
{
    if(size)
    {
        [self setSizeElement:[[FHIRInteger alloc] initWithValue:size]];
    }
    else
    {
        [self setSizeElement:nil];
    }
}


- (NSString *)hash
{
    if(self.hashElement)
    {
        return [self.hashElement value];
    }
    return nil;
}

- (void )setHash:(NSString *)hash
{
    if(hash)
    {
        [self setHashElement:[[FHIRString alloc] initWithValue:hash]];
    }
    else
    {
        [self setHashElement:nil];
    }
}


- (NSString *)location
{
    if(self.locationElement)
    {
        return [self.locationElement value];
    }
    return nil;
}

- (void )setLocation:(NSString *)location
{
    if(location)
    {
        [self setLocationElement:[[FHIRUri alloc] initWithValue:location]];
    }
    else
    {
        [self setLocationElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.masterIdentifier != nil )
        [result addValidationRange:[self.masterIdentifier validate]];
    if(self.identifier != nil )
        for(FHIRIdentifier *elem in self.identifier)
            [result addValidationRange:[elem validate]];
    if(self.subject != nil )
        [result addValidationRange:[self.subject validate]];
    if(self.type != nil )
        [result addValidationRange:[self.type validate]];
    if(self.class_ != nil )
        [result addValidationRange:[self.class_ validate]];
    if(self.author != nil )
        for(FHIRResourceReference *elem in self.author)
            [result addValidationRange:[elem validate]];
    if(self.custodian != nil )
        [result addValidationRange:[self.custodian validate]];
    if(self.policyManagerElement != nil )
        [result addValidationRange:[self.policyManagerElement validate]];
    if(self.authenticator != nil )
        [result addValidationRange:[self.authenticator validate]];
    if(self.createdElement != nil )
        [result addValidationRange:[self.createdElement validate]];
    if(self.indexedElement != nil )
        [result addValidationRange:[self.indexedElement validate]];
    if(self.statusElement != nil )
        [result addValidationRange:[self.statusElement validate]];
    if(self.docStatus != nil )
        [result addValidationRange:[self.docStatus validate]];
    if(self.relatesTo != nil )
        for(FHIRDocumentReferenceRelatesToComponent *elem in self.relatesTo)
            [result addValidationRange:[elem validate]];
    if(self.descriptionElement != nil )
        [result addValidationRange:[self.descriptionElement validate]];
    if(self.confidentiality != nil )
        for(FHIRCodeableConcept *elem in self.confidentiality)
            [result addValidationRange:[elem validate]];
    if(self.primaryLanguageElement != nil )
        [result addValidationRange:[self.primaryLanguageElement validate]];
    if(self.mimeTypeElement != nil )
        [result addValidationRange:[self.mimeTypeElement validate]];
    if(self.formatElement != nil )
        for(FHIRUri *elem in self.formatElement)
            [result addValidationRange:[elem validate]];
    if(self.sizeElement != nil )
        [result addValidationRange:[self.sizeElement validate]];
    if(self.hashElement != nil )
        [result addValidationRange:[self.hashElement validate]];
    if(self.locationElement != nil )
        [result addValidationRange:[self.locationElement validate]];
    if(self.service != nil )
        [result addValidationRange:[self.service validate]];
    if(self.context != nil )
        [result addValidationRange:[self.context validate]];
    
    return result;
}

@end
