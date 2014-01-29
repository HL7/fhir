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
 *
 * [FhirComposite("EncounterHospitalizationComponent")]
 * [Serializable]
 */

#import "FHIRElement.h"

#import "FHIREncounter.h"

@class FHIRIdentifier;
@class FHIRResourceReference;
@class FHIRCodeableConcept;
@class FHIRPeriod;
@class FHIREncounterHospitalizationAccomodationComponent;
@class FHIRBoolean;

@interface FHIREncounterHospitalizationComponent : FHIRElement

/*
 * Pre-admission identifier
 */
@property (nonatomic, strong) FHIRIdentifier *preAdmissionIdentifier;

/*
 * The location from which the patient came before admission
 */
@property (nonatomic, strong) FHIRResourceReference *origin;

/*
 * From where patient was admitted (physician referral, transfer)
 */
@property (nonatomic, strong) FHIRCodeableConcept *admitSource;

/*
 * Period during which the patient was admitted
 */
@property (nonatomic, strong) FHIRPeriod *period;

/*
 * Where the patient stays during this encounter
 */
@property (nonatomic, strong) NSArray/*<EncounterHospitalizationAccomodationComponent>*/ *accomodation;

/*
 * Dietary restrictions for the patient
 */
@property (nonatomic, strong) FHIRCodeableConcept *diet;

/*
 * Special courtesies (VIP, board member)
 */
@property (nonatomic, strong) NSArray/*<CodeableConcept>*/ *specialCourtesy;

/*
 * Wheelchair, translator, stretcher, etc
 */
@property (nonatomic, strong) NSArray/*<CodeableConcept>*/ *specialArrangement;

/*
 * Location to which the patient is discharged
 */
@property (nonatomic, strong) FHIRResourceReference *destination;

/*
 * Category or kind of location after discharge
 */
@property (nonatomic, strong) FHIRCodeableConcept *dischargeDisposition;

/*
 * The final diagnosis given a patient before release from the hospital after all testing, surgery, and workup are complete
 */
@property (nonatomic, strong) FHIRResourceReference *dischargeDiagnosis;

/*
 * Is this hospitalization a readmission?
 */
@property (nonatomic, strong) FHIRBoolean *reAdmissionElement;

@property (nonatomic, strong) NSNumber *reAdmission;

- (FHIRErrorList *)validate;

@end
