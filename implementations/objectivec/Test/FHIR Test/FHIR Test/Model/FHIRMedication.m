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
 * Definition of a Medication
 */
#import "FHIRMedication.h"

#import "FHIRString.h"
#import "FHIRCodeableConcept.h"
#import "FHIRBoolean.h"
#import "FHIRResourceReference.h"
#import "FHIRCode.h"
#import "FHIRMedicationProductComponent.h"
#import "FHIRMedicationPackageComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRMedication

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


- (NSNumber *)isBrand
{
    if(self.isBrandElement)
    {
        return [self.isBrandElement value];
    }
    return nil;
}

- (void )setIsBrand:(NSNumber *)isBrand
{
    if(isBrand)
    {
        [self setIsBrandElement:[[FHIRBoolean alloc] initWithValue:isBrand]];
    }
    else
    {
        [self setIsBrandElement:nil];
    }
}


- (kMedicationKind )kind
{
    return [FHIREnumHelper parseString:[self.kindElement value] enumType:kEnumTypeMedicationKind];
}

- (void )setKind:(kMedicationKind )kind
{
    [self setKindElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:kind enumType:kEnumTypeMedicationKind]]];
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.nameElement != nil )
        [result addValidationRange:[self.nameElement validate]];
    if(self.code != nil )
        [result addValidationRange:[self.code validate]];
    if(self.isBrandElement != nil )
        [result addValidationRange:[self.isBrandElement validate]];
    if(self.manufacturer != nil )
        [result addValidationRange:[self.manufacturer validate]];
    if(self.kindElement != nil )
        [result addValidationRange:[self.kindElement validate]];
    if(self.product != nil )
        [result addValidationRange:[self.product validate]];
    if(self.package != nil )
        [result addValidationRange:[self.package validate]];
    
    return result;
}

@end
