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
 * A manifest that defines a set of documents
 */
#import "FHIRDocumentManifest.h"

#import "FHIRIdentifier.h"
#import "FHIRResourceReference.h"
#import "FHIRCodeableConcept.h"
#import "FHIRDateTime.h"
#import "FHIRUri.h"
#import "FHIRCode.h"
#import "FHIRString.h"

#import "FHIRErrorList.h"

@implementation FHIRDocumentManifest

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


- (NSString *)source
{
    if(self.sourceElement)
    {
        return [self.sourceElement value];
    }
    return nil;
}

- (void )setSource:(NSString *)source
{
    if(source)
    {
        [self setSourceElement:[[FHIRUri alloc] initWithValue:source]];
    }
    else
    {
        [self setSourceElement:nil];
    }
}


- (NSString *)status
{
    if(self.statusElement)
    {
        return [self.statusElement value];
    }
    return nil;
}

- (void )setStatus:(NSString *)status
{
    if(status)
    {
        [self setStatusElement:[[FHIRCode alloc] initWithValue:status]];
    }
    else
    {
        [self setStatusElement:nil];
    }
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
        for(FHIRResourceReference *elem in self.subject)
            [result addValidationRange:[elem validate]];
    if(self.recipient != nil )
        for(FHIRResourceReference *elem in self.recipient)
            [result addValidationRange:[elem validate]];
    if(self.type != nil )
        [result addValidationRange:[self.type validate]];
    if(self.author != nil )
        for(FHIRResourceReference *elem in self.author)
            [result addValidationRange:[elem validate]];
    if(self.createdElement != nil )
        [result addValidationRange:[self.createdElement validate]];
    if(self.sourceElement != nil )
        [result addValidationRange:[self.sourceElement validate]];
    if(self.statusElement != nil )
        [result addValidationRange:[self.statusElement validate]];
    if(self.supercedes != nil )
        [result addValidationRange:[self.supercedes validate]];
    if(self.descriptionElement != nil )
        [result addValidationRange:[self.descriptionElement validate]];
    if(self.confidentiality != nil )
        [result addValidationRange:[self.confidentiality validate]];
    if(self.content != nil )
        for(FHIRResourceReference *elem in self.content)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
