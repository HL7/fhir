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
 * Content in a format defined elsewhere
 */
#import "FHIRAttachment.h"

#import "FHIRCode.h"
#import "FHIRBase64Binary.h"
#import "FHIRUri.h"
#import "FHIRInteger.h"
#import "FHIRString.h"

#import "FHIRErrorList.h"

@implementation FHIRAttachment

- (NSString *)contentType
{
    if(self.contentTypeElement)
    {
        return [self.contentTypeElement value];
    }
    return nil;
}

- (void )setContentType:(NSString *)contentType
{
    if(contentType)
    {
        [self setContentTypeElement:[[FHIRCode alloc] initWithValue:contentType]];
    }
    else
    {
        [self setContentTypeElement:nil];
    }
}


- (NSString *)language
{
    if(self.languageElement)
    {
        return [self.languageElement value];
    }
    return nil;
}

- (void )setLanguage:(NSString *)language
{
    if(language)
    {
        [self setLanguageElement:[[FHIRCode alloc] initWithValue:language]];
    }
    else
    {
        [self setLanguageElement:nil];
    }
}


- (NSData *)data
{
    if(self.dataElement)
    {
        return [self.dataElement value];
    }
    return nil;
}

- (void )setData:(NSData *)data
{
    if(data)
    {
        [self setDataElement:[[FHIRBase64Binary alloc] initWithValue:data]];
    }
    else
    {
        [self setDataElement:nil];
    }
}


- (NSString *)url
{
    if(self.urlElement)
    {
        return [self.urlElement value];
    }
    return nil;
}

- (void )setUrl:(NSString *)url
{
    if(url)
    {
        [self setUrlElement:[[FHIRUri alloc] initWithValue:url]];
    }
    else
    {
        [self setUrlElement:nil];
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


- (NSData *)hash
{
    if(self.hashElement)
    {
        return [self.hashElement value];
    }
    return nil;
}

- (void )setHash:(NSData *)hash
{
    if(hash)
    {
        [self setHashElement:[[FHIRBase64Binary alloc] initWithValue:hash]];
    }
    else
    {
        [self setHashElement:nil];
    }
}


- (NSString *)title
{
    if(self.titleElement)
    {
        return [self.titleElement value];
    }
    return nil;
}

- (void )setTitle:(NSString *)title
{
    if(title)
    {
        [self setTitleElement:[[FHIRString alloc] initWithValue:title]];
    }
    else
    {
        [self setTitleElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.contentTypeElement != nil )
        [result addValidationRange:[self.contentTypeElement validate]];
    if(self.languageElement != nil )
        [result addValidationRange:[self.languageElement validate]];
    if(self.dataElement != nil )
        [result addValidationRange:[self.dataElement validate]];
    if(self.urlElement != nil )
        [result addValidationRange:[self.urlElement validate]];
    if(self.sizeElement != nil )
        [result addValidationRange:[self.sizeElement validate]];
    if(self.hashElement != nil )
        [result addValidationRange:[self.hashElement validate]];
    if(self.titleElement != nil )
        [result addValidationRange:[self.titleElement validate]];
    
    return result;
}

@end
