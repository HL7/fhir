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
 * Dispensing a medication to a named patient
 *
 * [FhirResource("MedicationDispense")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRCode;
@class FHIRResourceReference;
@class FHIRMedicationDispenseDispenseComponent;
@class FHIRMedicationDispenseSubstitutionComponent;

@interface FHIRMedicationDispense : FHIRResource

/*
 * A code specifying the state of the dispense event.
 */
typedef enum 
{
    kMedicationDispenseStatusInProgress, // The dispense has started but has not yet completed.
    kMedicationDispenseStatusOnHold, // Actions implied by the administration have been temporarily halted, but are expected to continue later. May also be called "suspended".
    kMedicationDispenseStatusCompleted, // All actions that are implied by the dispense have occurred.
    kMedicationDispenseStatusEnteredInError, // The dispense was entered in error and therefore nullified.
    kMedicationDispenseStatusStopped, // Actions implied by the dispense have been permanently halted, before all of them occurred.
} kMedicationDispenseStatus;

/*
 * External identifier
 */
@property (nonatomic, strong) FHIRIdentifier *identifier;

/*
 * in progress | on hold | completed | entered in error | stopped
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kMedicationDispenseStatus status;

/*
 * Who the dispense is for
 */
@property (nonatomic, strong) FHIRResourceReference *patient;

/*
 * Practitioner responsible for dispensing medication
 */
@property (nonatomic, strong) FHIRResourceReference *dispenser;

/*
 * Medication order that authorises the dispense
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *authorizingPrescription;

/*
 * Details for individual dispensed medicationdetails
 */
@property (nonatomic, strong) NSArray/*<MedicationDispenseDispenseComponent>*/ *dispense;

/*
 * Deals with substitution of one medicine for another
 */
@property (nonatomic, strong) FHIRMedicationDispenseSubstitutionComponent *substitution;

- (FHIRErrorList *)validate;

@end
