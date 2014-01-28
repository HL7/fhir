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
 * A grouping of people or organizations with a common purpose
 */
#import "FHIROrganization.h"

#import "FHIRIdentifier.h"
#import "FHIRString.h"
#import "FHIRCodeableConcept.h"
#import "FHIRContact.h"
#import "FHIRAddress.h"
#import "FHIRResourceReference.h"
#import "FHIROrganizationContactComponent.h"
#import "FHIRBoolean.h"

#import "FHIRErrorList.h"

@implementation FHIROrganization

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


- (NSNumber *)active
{
    if(self.activeElement)
    {
        return [self.activeElement value];
    }
    return nil;
}

- (void )setActive:(NSNumber *)active
{
    if(active)
    {
        [self setActiveElement:[[FHIRBoolean alloc] initWithValue:active]];
    }
    else
    {
        [self setActiveElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.identifier != nil )
        for(FHIRIdentifier *elem in self.identifier)
            [result addValidationRange:[elem validate]];
    if(self.nameElement != nil )
        [result addValidationRange:[self.nameElement validate]];
    if(self.type != nil )
        [result addValidationRange:[self.type validate]];
    if(self.telecom != nil )
        for(FHIRContact *elem in self.telecom)
            [result addValidationRange:[elem validate]];
    if(self.address != nil )
        for(FHIRAddress *elem in self.address)
            [result addValidationRange:[elem validate]];
    if(self.partOf != nil )
        [result addValidationRange:[self.partOf validate]];
    if(self.contact != nil )
        for(FHIROrganizationContactComponent *elem in self.contact)
            [result addValidationRange:[elem validate]];
    if(self.location != nil )
        for(FHIRResourceReference *elem in self.location)
            [result addValidationRange:[elem validate]];
    if(self.activeElement != nil )
        [result addValidationRange:[self.activeElement validate]];
    
    return result;
}

@end
