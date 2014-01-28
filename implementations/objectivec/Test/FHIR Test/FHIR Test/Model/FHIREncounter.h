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
 * An interaction during which services are provided to the patient
 *
 * [FhirResource("Encounter")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRCode;
@class FHIRCodeableConcept;
@class FHIRResourceReference;
@class FHIREncounterParticipantComponent;
@class FHIRPeriod;
@class FHIRDuration;
@class FHIREncounterHospitalizationComponent;
@class FHIREncounterLocationComponent;

@interface FHIREncounter : FHIRResource

/*
 * Classification of the encounter
 */
typedef enum 
{
    kEncounterClassInpatient, // An encounter during which the patient is hospitalized and stays overnight.
    kEncounterClassOutpatient, // An encounter during which the patient is not hospitalized overnight.
    kEncounterClassAmbulatory, // An encounter where the patient visits the practitioner in his/her office, e.g. a G.P. visit.
    kEncounterClassEmergency, // An encounter where the patient needs urgent care.
    kEncounterClassHome, // An encounter where the practitioner visits the patient at his/her home.
    kEncounterClassField, // An encounter taking place outside the regular environment for giving care.
    kEncounterClassDaytime, // An encounter where the patient needs more prolonged treatment or investigations than outpatients, but who do not need to stay in the hospital overnight.
    kEncounterClassVirtual, // An encounter that takes place where the patient and practitioner do not physically meet but use electronic means for contact.
} kEncounterClass;

/*
 * Current state of the encounter
 */
typedef enum 
{
    kEncounterStatePlanned, // The Encounter has not yet started.
    kEncounterStateInProgress, // The Encounter has begun and the patient is present / the practitioner and the patient are meeting.
    kEncounterStateOnleave, // The Encounter has begun, but the patient is temporarily on leave.
    kEncounterStateFinished, // The Encounter has ended.
    kEncounterStateCancelled, // The Encounter has ended before it has begun.
} kEncounterState;

/*
 * Identifier(s) by which this encounter is known
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * planned | in progress | onleave | finished | cancelled
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kEncounterState status;

/*
 * inpatient | outpatient | ambulatory | emergency +
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *class_Element;

@property (nonatomic) kEncounterClass class_;

/*
 * Specific type of encounter
 */
@property (nonatomic, strong) NSArray/*<CodeableConcept>*/ *type;

/*
 * The patient present at the encounter
 */
@property (nonatomic, strong) FHIRResourceReference *subject;

/*
 * List of participants involved in the encounter
 */
@property (nonatomic, strong) NSArray/*<EncounterParticipantComponent>*/ *participant;

/*
 * The start and end time of the encounter
 */
@property (nonatomic, strong) FHIRPeriod *period;

/*
 * Quantity of time the encounter lasted
 */
@property (nonatomic, strong) FHIRDuration *length;

/*
 * Reason the encounter takes place (code)
 */
@property (nonatomic, strong) FHIRCodeableConcept *reason;

/*
 * Reason the encounter takes place (resource)
 */
@property (nonatomic, strong) FHIRResourceReference *indication;

/*
 * Indicates the urgency of the encounter
 */
@property (nonatomic, strong) FHIRCodeableConcept *priority;

/*
 * Details about an admission to a clinic
 */
@property (nonatomic, strong) FHIREncounterHospitalizationComponent *hospitalization;

/*
 * List of locations the patient has been at
 */
@property (nonatomic, strong) NSArray/*<EncounterLocationComponent>*/ *location;

/*
 * Department or team providing care
 */
@property (nonatomic, strong) FHIRResourceReference *serviceProvider;

/*
 * Another Encounter this encounter is part of
 */
@property (nonatomic, strong) FHIRResourceReference *partOf;

- (FHIRErrorList *)validate;

@end
