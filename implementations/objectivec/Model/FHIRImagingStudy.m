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
 * A set of images produced in single study (one or more series of references images)
 */
#import "FHIRImagingStudy.h"

#import "FHIRDateTime.h"
#import "FHIRResourceReference.h"
#import "FHIROid.h"
#import "FHIRIdentifier.h"
#import "FHIRCode.h"
#import "FHIRUri.h"
#import "FHIRInteger.h"
#import "FHIRString.h"
#import "FHIRCoding.h"
#import "FHIRImagingStudySeriesComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRImagingStudy

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


- (NSArray /*<kImagingModality>*/ *)modality
{
    if(self.modalityElement)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(FHIRCode *elem in self.modalityElement)
            [array addObject:[NSNumber numberWithInt:[FHIREnumHelper parseString:[elem value] enumType:kEnumTypeImagingModality]]];
        return [NSArray arrayWithArray:array];
    }
    return nil;
}

- (void )setModality:(NSArray /*<kImagingModality>*/ *)modality
{
    if(modality)
    {
        NSMutableArray *array = [NSMutableArray new];
        for(NSNumber *value in self.modality)
            [array addObject:[FHIREnumHelper enumToString:[value intValue] enumType:kEnumTypeImagingModality]];
        [self setModalityElement:[NSArray arrayWithArray:array]];
    }
    else
    {
        [self setModalityElement:nil];
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


- (NSNumber *)numberOfSeries
{
    if(self.numberOfSeriesElement)
    {
        return [self.numberOfSeriesElement value];
    }
    return nil;
}

- (void )setNumberOfSeries:(NSNumber *)numberOfSeries
{
    if(numberOfSeries)
    {
        [self setNumberOfSeriesElement:[[FHIRInteger alloc] initWithValue:numberOfSeries]];
    }
    else
    {
        [self setNumberOfSeriesElement:nil];
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


- (NSString *)clinicalInformation
{
    if(self.clinicalInformationElement)
    {
        return [self.clinicalInformationElement value];
    }
    return nil;
}

- (void )setClinicalInformation:(NSString *)clinicalInformation
{
    if(clinicalInformation)
    {
        [self setClinicalInformationElement:[[FHIRString alloc] initWithValue:clinicalInformation]];
    }
    else
    {
        [self setClinicalInformationElement:nil];
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
    
    if(self.dateTimeElement != nil )
        [result addValidationRange:[self.dateTimeElement validate]];
    if(self.subject != nil )
        [result addValidationRange:[self.subject validate]];
    if(self.uidElement != nil )
        [result addValidationRange:[self.uidElement validate]];
    if(self.accessionNo != nil )
        [result addValidationRange:[self.accessionNo validate]];
    if(self.identifier != nil )
        for(FHIRIdentifier *elem in self.identifier)
            [result addValidationRange:[elem validate]];
    if(self.order != nil )
        for(FHIRResourceReference *elem in self.order)
            [result addValidationRange:[elem validate]];
    if(self.modalityElement != nil )
        for(FHIRCode *elem in self.modalityElement)
            [result addValidationRange:[elem validate]];
    if(self.referrer != nil )
        [result addValidationRange:[self.referrer validate]];
    if(self.availabilityElement != nil )
        [result addValidationRange:[self.availabilityElement validate]];
    if(self.urlElement != nil )
        [result addValidationRange:[self.urlElement validate]];
    if(self.numberOfSeriesElement != nil )
        [result addValidationRange:[self.numberOfSeriesElement validate]];
    if(self.numberOfInstancesElement != nil )
        [result addValidationRange:[self.numberOfInstancesElement validate]];
    if(self.clinicalInformationElement != nil )
        [result addValidationRange:[self.clinicalInformationElement validate]];
    if(self.procedure != nil )
        for(FHIRCoding *elem in self.procedure)
            [result addValidationRange:[elem validate]];
    if(self.interpreter != nil )
        [result addValidationRange:[self.interpreter validate]];
    if(self.descriptionElement != nil )
        [result addValidationRange:[self.descriptionElement validate]];
    if(self.series != nil )
        for(FHIRImagingStudySeriesComponent *elem in self.series)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
