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
 * An instance of a manufactured thing that is used in the provision of healthcare
 */
#import "FHIRDevice.h"

#import "FHIRIdentifier.h"
#import "FHIRCodeableConcept.h"
#import "FHIRString.h"
#import "FHIRDate.h"
#import "FHIRResourceReference.h"
#import "FHIRContact.h"
#import "FHIRUri.h"

#import "FHIRErrorList.h"

@implementation FHIRDevice

- (NSString *)manufacturer
{
    if(self.manufacturerElement)
    {
        return [self.manufacturerElement value];
    }
    return nil;
}

- (void )setManufacturer:(NSString *)manufacturer
{
    if(manufacturer)
    {
        [self setManufacturerElement:[[FHIRString alloc] initWithValue:manufacturer]];
    }
    else
    {
        [self setManufacturerElement:nil];
    }
}


- (NSString *)model
{
    if(self.modelElement)
    {
        return [self.modelElement value];
    }
    return nil;
}

- (void )setModel:(NSString *)model
{
    if(model)
    {
        [self setModelElement:[[FHIRString alloc] initWithValue:model]];
    }
    else
    {
        [self setModelElement:nil];
    }
}


- (NSString *)version
{
    if(self.versionElement)
    {
        return [self.versionElement value];
    }
    return nil;
}

- (void )setVersion:(NSString *)version
{
    if(version)
    {
        [self setVersionElement:[[FHIRString alloc] initWithValue:version]];
    }
    else
    {
        [self setVersionElement:nil];
    }
}


- (NSString *)expiry
{
    if(self.expiryElement)
    {
        return [self.expiryElement value];
    }
    return nil;
}

- (void )setExpiry:(NSString *)expiry
{
    if(expiry)
    {
        [self setExpiryElement:[[FHIRDate alloc] initWithValue:expiry]];
    }
    else
    {
        [self setExpiryElement:nil];
    }
}


- (NSString *)udi
{
    if(self.udiElement)
    {
        return [self.udiElement value];
    }
    return nil;
}

- (void )setUdi:(NSString *)udi
{
    if(udi)
    {
        [self setUdiElement:[[FHIRString alloc] initWithValue:udi]];
    }
    else
    {
        [self setUdiElement:nil];
    }
}


- (NSString *)lotNumber
{
    if(self.lotNumberElement)
    {
        return [self.lotNumberElement value];
    }
    return nil;
}

- (void )setLotNumber:(NSString *)lotNumber
{
    if(lotNumber)
    {
        [self setLotNumberElement:[[FHIRString alloc] initWithValue:lotNumber]];
    }
    else
    {
        [self setLotNumberElement:nil];
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
    
    if(self.identifier != nil )
        for(FHIRIdentifier *elem in self.identifier)
            [result addValidationRange:[elem validate]];
    if(self.type != nil )
        [result addValidationRange:[self.type validate]];
    if(self.manufacturerElement != nil )
        [result addValidationRange:[self.manufacturerElement validate]];
    if(self.modelElement != nil )
        [result addValidationRange:[self.modelElement validate]];
    if(self.versionElement != nil )
        [result addValidationRange:[self.versionElement validate]];
    if(self.expiryElement != nil )
        [result addValidationRange:[self.expiryElement validate]];
    if(self.udiElement != nil )
        [result addValidationRange:[self.udiElement validate]];
    if(self.lotNumberElement != nil )
        [result addValidationRange:[self.lotNumberElement validate]];
    if(self.owner != nil )
        [result addValidationRange:[self.owner validate]];
    if(self.location != nil )
        [result addValidationRange:[self.location validate]];
    if(self.patient != nil )
        [result addValidationRange:[self.patient validate]];
    if(self.contact != nil )
        for(FHIRContact *elem in self.contact)
            [result addValidationRange:[elem validate]];
    if(self.urlElement != nil )
        [result addValidationRange:[self.urlElement validate]];
    
    return result;
}

@end
