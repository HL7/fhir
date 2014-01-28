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
 * Prescription of medication to for patient
 *
 * [FhirResource("MedicationPrescription")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRDateTime;
@class FHIRCode;
@class FHIRResourceReference;
@class FHIRElement;
@class FHIRMedicationPrescriptionDosageInstructionComponent;
@class FHIRMedicationPrescriptionDispenseComponent;
@class FHIRMedicationPrescriptionSubstitutionComponent;

@interface FHIRMedicationPrescription : FHIRResource

/*
 * A code specifying the state of the prescribing event. Describes the lifecycle of the prescription.
 */
typedef enum 
{
    kMedicationPrescriptionStatusActive, // The prescription is 'actionable', but not all actions that are implied by it have occurred yet.
    kMedicationPrescriptionStatusOnHold, // Actions implied by the prescription have been temporarily halted, but are expected to continue later.  May also be called "suspended".
    kMedicationPrescriptionStatusCompleted, // All actions that are implied by the prescription have occurred (this will rarely be made explicit).
    kMedicationPrescriptionStatusEnteredInError, // The prescription was entered in error and therefore nullified.
    kMedicationPrescriptionStatusStopped, // Actions implied by the prescription have been permanently halted, before all of them occurred.
    kMedicationPrescriptionStatusSuperceded, // The prescription was replaced by a newer one, which encompasses all the information in the previous one.
} kMedicationPrescriptionStatus;

/*
 * External identifier
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * When prescription was authorized
 */
@property (nonatomic, strong) FHIRDateTime *dateWrittenElement;

@property (nonatomic, strong) NSString *dateWritten;

/*
 * active | on hold | completed | entered in error | stopped | superceded
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kMedicationPrescriptionStatus status;

/*
 * Who prescription is for
 */
@property (nonatomic, strong) FHIRResourceReference *patient;

/*
 * Who ordered the medication(s)
 */
@property (nonatomic, strong) FHIRResourceReference *prescriber;

/*
 * Created during encounter / admission / stay
 */
@property (nonatomic, strong) FHIRResourceReference *encounter;

/*
 * Reason or indication for writing the prescription
 */
@property (nonatomic, strong) FHIRElement *reason;

/*
 * Medication to be taken
 */
@property (nonatomic, strong) FHIRResourceReference *medication;

/*
 * How medication should be taken
 */
@property (nonatomic, strong) NSArray/*<MedicationPrescriptionDosageInstructionComponent>*/ *dosageInstruction;

/*
 * Medication supply authorization
 */
@property (nonatomic, strong) FHIRMedicationPrescriptionDispenseComponent *dispense;

/*
 * Any restrictions on medication substitution?
 */
@property (nonatomic, strong) FHIRMedicationPrescriptionSubstitutionComponent *substitution;

- (FHIRErrorList *)validate;

@end
