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
 * Healthcare plan for patient
 *
 * [FhirResource("CarePlan")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRResourceReference;
@class FHIRCode;
@class FHIRPeriod;
@class FHIRDateTime;
@class FHIRCarePlanParticipantComponent;
@class FHIRCarePlanGoalComponent;
@class FHIRCarePlanActivityComponent;
@class FHIRString;

@interface FHIRCarePlan : FHIRResource

/*
 * Indicates whether the plan is currently being acted upon, represents future intentions or is now just historical record.
 */
typedef enum 
{
    kCarePlanStatusPlanned, // The plan is in development or awaiting use but is not yet intended to be acted upon.
    kCarePlanStatusActive, // The plan is intended to be followed and used as part of patient care.
    kCarePlanStatusCompleted, // The plan is no longer in use and is not expected to be followed or used in patient care.
} kCarePlanStatus;

/*
 * High-level categorization of the type of activity in a care plan.
 */
typedef enum 
{
    kCarePlanActivityCategoryDiet, // Plan for the patient to consume food of a specified nature.
    kCarePlanActivityCategoryDrug, // Plan for the patient to consume/receive a drug, vaccine or other product.
    kCarePlanActivityCategoryEncounter, // Plan to meet or communicate with the patient (in-patient, out-patient, phone call, etc.).
    kCarePlanActivityCategoryObservation, // Plan to capture information about a patient (vitals, labs, diagnostic images, etc.).
    kCarePlanActivityCategoryProcedure, // Plan to modify the patient in some way (surgery, physiotherapy, education, counseling, etc.).
    kCarePlanActivityCategorySupply, // Plan to provide something to the patient (medication, medical supply, etc.).
    kCarePlanActivityCategoryOther, // Some other form of action.
} kCarePlanActivityCategory;

/*
 * Indicates whether the goal has been met and is still being targeted
 */
typedef enum 
{
    kCarePlanGoalStatusInProgress, // The goal is being sought but has not yet been reached.  (Also applies if goal was reached in the past but there has been regression and goal is being sought again).
    kCarePlanGoalStatusAchieved, // The goal has been met and no further action is needed.
    kCarePlanGoalStatusSustaining, // The goal has been met, but ongoing activity is needed to sustain the goal objective.
    kCarePlanGoalStatusCancelled, // The goal is no longer being sought.
} kCarePlanGoalStatus;

/*
 * Indicates where the activity is at in its overall life cycle
 */
typedef enum 
{
    kCarePlanActivityStatusNotStarted, // Activity is planned but no action has yet been taken.
    kCarePlanActivityStatusScheduled, // Appointment or other booking has occurred but activity has not yet begun.
    kCarePlanActivityStatusInProgress, // Activity has been started but is not yet complete.
    kCarePlanActivityStatusOnHold, // Activity was started but has temporarily ceased with an expectation of resumption at a future time.
    kCarePlanActivityStatusCompleted, // The activities have been completed (more or less) as planned.
    kCarePlanActivityStatusCancelled, // The activities have been ended prior to completion (perhaps even before they were started).
} kCarePlanActivityStatus;

/*
 * External Ids for this plan
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * Who care plan is for
 */
@property (nonatomic, strong) FHIRResourceReference *patient;

/*
 * planned | active | completed
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kCarePlanStatus status;

/*
 * Time period plan covers
 */
@property (nonatomic, strong) FHIRPeriod *period;

/*
 * When last updated
 */
@property (nonatomic, strong) FHIRDateTime *modifiedElement;

@property (nonatomic, strong) NSString *modified;

/*
 * Health issues this plan addresses
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *concern;

/*
 * Who's involved in plan?
 */
@property (nonatomic, strong) NSArray/*<CarePlanParticipantComponent>*/ *participant;

/*
 * Desired outcome of plan
 */
@property (nonatomic, strong) NSArray/*<CarePlanGoalComponent>*/ *goal;

/*
 * Action to occur as part of plan
 */
@property (nonatomic, strong) NSArray/*<CarePlanActivityComponent>*/ *activity;

/*
 * Comments about the plan
 */
@property (nonatomic, strong) FHIRString *notesElement;

@property (nonatomic, strong) NSString *notes;

- (FHIRErrorList *)validate;

@end
