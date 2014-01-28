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
 * null
 *
 * [FhirComposite("MedicationStatementDosageComponent")]
 * [Serializable]
 */

#import "FHIRElement.h"

#import "FHIRMedicationStatement.h"

@class FHIRSchedule;
@class FHIRElement;
@class FHIRCodeableConcept;
@class FHIRQuantity;
@class FHIRRatio;

@interface FHIRMedicationStatementDosageComponent : FHIRElement

/*
 * When/how often was medication taken?
 */
@property (nonatomic, strong) FHIRSchedule *timing;

/*
 * Take "as needed" f(or x)
 */
@property (nonatomic, strong) FHIRElement *asNeeded;

/*
 * Where on body was medication administered?
 */
@property (nonatomic, strong) FHIRCodeableConcept *site;

/*
 * How did the medication enter the body?
 */
@property (nonatomic, strong) FHIRCodeableConcept *route;

/*
 * Technique used to administer medication
 */
@property (nonatomic, strong) FHIRCodeableConcept *method;

/*
 * Amount administered in one dose
 */
@property (nonatomic, strong) FHIRQuantity *quantity;

/*
 * Dose quantity per unit of time
 */
@property (nonatomic, strong) FHIRRatio *rate;

/*
 * Maximum dose that was consumed per unit of time
 */
@property (nonatomic, strong) FHIRRatio *maxDosePerPeriod;

- (FHIRErrorList *)validate;

@end
