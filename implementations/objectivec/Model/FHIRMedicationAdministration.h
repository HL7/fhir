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
 * Administration of medication to a patient
 *
 * [FhirResource("MedicationAdministration")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRCode;
@class FHIRResourceReference;
@class FHIRBoolean;
@class FHIRCodeableConcept;
@class FHIRPeriod;
@class FHIRMedicationAdministrationDosageComponent;

@interface FHIRMedicationAdministration : FHIRResource

/*
 * A set of codes indicating the current status of a MedicationAdministration
 */
typedef enum 
{
    kMedicationAdministrationStatusInProgress, // The administration has started but has not yet completed.
    kMedicationAdministrationStatusOnHold, // Actions implied by the administration have been temporarily halted, but are expected to continue later. May also be called "suspended".
    kMedicationAdministrationStatusCompleted, // All actions that are implied by the administration have occurred.
    kMedicationAdministrationStatusEnteredInError, // The administration was entered in error and therefore nullified.
    kMedicationAdministrationStatusStopped, // Actions implied by the administration have been permanently halted, before all of them occurred.
} kMedicationAdministrationStatus;

/*
 * External identifier
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * in progress | on hold | completed | entered in error | stopped
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kMedicationAdministrationStatus status;

/*
 * Who received medication?
 */
@property (nonatomic, strong) FHIRResourceReference *patient;

/*
 * Who administered substance?
 */
@property (nonatomic, strong) FHIRResourceReference *practitioner;

/*
 * Encounter administered as part of
 */
@property (nonatomic, strong) FHIRResourceReference *encounter;

/*
 * Order administration performed against
 */
@property (nonatomic, strong) FHIRResourceReference *prescription;

/*
 * True if medication not administered
 */
@property (nonatomic, strong) FHIRBoolean *wasNotGivenElement;

@property (nonatomic, strong) NSNumber *wasNotGiven;

/*
 * Reason administration not performed
 */
@property (nonatomic, strong) NSArray/*<CodeableConcept>*/ *reasonNotGiven;

/*
 * Start and end time of administration
 */
@property (nonatomic, strong) FHIRPeriod *whenGiven;

/*
 * What was administered?
 */
@property (nonatomic, strong) FHIRResourceReference *medication;

/*
 * Device used to administer
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *device;

/*
 * Medicine administration instructions to the patient/carer
 */
@property (nonatomic, strong) NSArray/*<MedicationAdministrationDosageComponent>*/ *dosage;

- (FHIRErrorList *)validate;

@end
