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
 *
 * [FhirResource("Medication")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRString;
@class FHIRCodeableConcept;
@class FHIRBoolean;
@class FHIRResourceReference;
@class FHIRCode;
@class FHIRMedicationProductComponent;
@class FHIRMedicationPackageComponent;

@interface FHIRMedication : FHIRResource

/*
 * Whether the medication is a product or a package
 */
typedef enum 
{
    kMedicationKindProduct, // The medication is a product.
    kMedicationKindPackage, // The medication is a package - a contained group of one of more products.
} kMedicationKind;

/*
 * Common / Commercial name
 */
@property (nonatomic, strong) FHIRString *nameElement;

@property (nonatomic, strong) NSString *name;

/*
 * Codes that identify this medication
 */
@property (nonatomic, strong) FHIRCodeableConcept *code;

/*
 * True if a brand
 */
@property (nonatomic, strong) FHIRBoolean *isBrandElement;

@property (nonatomic, strong) NSNumber *isBrand;

/*
 * Manufacturer of the item
 */
@property (nonatomic, strong) FHIRResourceReference *manufacturer;

/*
 * product | package
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *kindElement;

@property (nonatomic) kMedicationKind kind;

/*
 * Administrable medication details
 */
@property (nonatomic, strong) FHIRMedicationProductComponent *product;

/*
 * Details about packaged medications
 */
@property (nonatomic, strong) FHIRMedicationPackageComponent *package;

- (FHIRErrorList *)validate;

@end
