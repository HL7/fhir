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
#import "FHIRImagingStudySeriesInstanceComponent.h"

#import "FHIRInteger.h"
#import "FHIROid.h"
#import "FHIRString.h"
#import "FHIRUri.h"
#import "FHIRResourceReference.h"

#import "FHIRErrorList.h"

@implementation FHIRImagingStudySeriesInstanceComponent

- (NSNumber *)number
{
    if(self.numberElement)
    {
        return [self.numberElement value];
    }
    return nil;
}

- (void )setNumber:(NSNumber *)number
{
    if(number)
    {
        [self setNumberElement:[[FHIRInteger alloc] initWithValue:number]];
    }
    else
    {
        [self setNumberElement:nil];
    }
}


- (NSString *)uid
{
    if(self.uidElement)
    {
        return [self.uidElement value];
    }
    return nil;
}

- (void )setUid:(NSString *)uid
{
    if(uid)
    {
        [self setUidElement:[[FHIROid alloc] initWithValue:uid]];
    }
    else
    {
        [self setUidElement:nil];
    }
}


- (NSString *)sopclass
{
    if(self.sopclassElement)
    {
        return [self.sopclassElement value];
    }
    return nil;
}

- (void )setSopclass:(NSString *)sopclass
{
    if(sopclass)
    {
        [self setSopclassElement:[[FHIROid alloc] initWithValue:sopclass]];
    }
    else
    {
        [self setSopclassElement:nil];
    }
}


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
        [self setTypeElement:[[FHIRString alloc] initWithValue:type]];
    }
    else
    {
        [self setTypeElement:nil];
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


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.numberElement != nil )
        [result addValidationRange:[self.numberElement validate]];
    if(self.uidElement != nil )
        [result addValidationRange:[self.uidElement validate]];
    if(self.sopclassElement != nil )
        [result addValidationRange:[self.sopclassElement validate]];
    if(self.typeElement != nil )
        [result addValidationRange:[self.typeElement validate]];
    if(self.titleElement != nil )
        [result addValidationRange:[self.titleElement validate]];
    if(self.urlElement != nil )
        [result addValidationRange:[self.urlElement validate]];
    if(self.attachment != nil )
        [result addValidationRange:[self.attachment validate]];
    
    return result;
}

@end
