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
#import "FHIRImagingStudySeriesComponent.h"

#import "FHIRInteger.h"
#import "FHIRCode.h"
#import "FHIROid.h"
#import "FHIRString.h"
#import "FHIRUri.h"
#import "FHIRCoding.h"
#import "FHIRDateTime.h"
#import "FHIRImagingStudySeriesInstanceComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRImagingStudySeriesComponent

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


- (kModality )modality
{
    return [FHIREnumHelper parseString:[self.modalityElement value] enumType:kEnumTypeModality];
}

- (void )setModality:(kModality )modality
{
    [self setModalityElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:modality enumType:kEnumTypeModality]]];
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


- (NSNumber *)numberOfInstances
{
    if(self.numberOfInstancesElement)
    {
        return [self.numberOfInstancesElement value];
    }
    return nil;
}

- (void )setNumberOfInstances:(NSNumber *)numberOfInstances
{
    if(numberOfInstances)
    {
        [self setNumberOfInstancesElement:[[FHIRInteger alloc] initWithValue:numberOfInstances]];
    }
    else
    {
        [self setNumberOfInstancesElement:nil];
    }
}


- (kInstanceAvailability )availability
{
    return [FHIREnumHelper parseString:[self.availabilityElement value] enumType:kEnumTypeInstanceAvailability];
}

- (void )setAvailability:(kInstanceAvailability )availability
{
    [self setAvailabilityElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:availability enumType:kEnumTypeInstanceAvailability]]];
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


- (NSString *)dateTime
{
    if(self.dateTimeElement)
    {
        return [self.dateTimeElement value];
    }
    return nil;
}

- (void )setDateTime:(NSString *)dateTime
{
    if(dateTime)
    {
        [self setDateTimeElement:[[FHIRDateTime alloc] initWithValue:dateTime]];
    }
    else
    {
        [self setDateTimeElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.numberElement != nil )
        [result addValidationRange:[self.numberElement validate]];
    if(self.modalityElement != nil )
        [result addValidationRange:[self.modalityElement validate]];
    if(self.uidElement != nil )
        [result addValidationRange:[self.uidElement validate]];
    if(self.descriptionElement != nil )
        [result addValidationRange:[self.descriptionElement validate]];
    if(self.numberOfInstancesElement != nil )
        [result addValidationRange:[self.numberOfInstancesElement validate]];
    if(self.availabilityElement != nil )
        [result addValidationRange:[self.availabilityElement validate]];
    if(self.urlElement != nil )
        [result addValidationRange:[self.urlElement validate]];
    if(self.bodySite != nil )
        [result addValidationRange:[self.bodySite validate]];
    if(self.dateTimeElement != nil )
        [result addValidationRange:[self.dateTimeElement validate]];
    if(self.instance != nil )
        for(FHIRImagingStudySeriesInstanceComponent *elem in self.instance)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
