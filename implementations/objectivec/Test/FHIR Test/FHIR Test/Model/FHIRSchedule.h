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
 * A schedule that specifies an event that may occur multiple times
 *
 * [FhirComposite("Schedule")]
 * [Serializable]
 */

#import "FHIRElement.h"


@class FHIRPeriod;
@class FHIRScheduleRepeatComponent;

@interface FHIRSchedule : FHIRElement

/*
 * A unit of time (units from UCUM)
 */
typedef enum 
{
    kUnitsOfTimeS, // second.
    kUnitsOfTimeMin, // minute.
    kUnitsOfTimeH, // hour.
    kUnitsOfTimeD, // day.
    kUnitsOfTimeWk, // week.
    kUnitsOfTimeMo, // month.
    kUnitsOfTimeA, // year.
} kUnitsOfTime;

/*
 * Real world event that the schedule relates to
 */
typedef enum 
{
    kEventTimingHS, // event occurs [duration] before the hour of sleep (or trying to).
    kEventTimingWAKE, // event occurs [duration] after waking.
    kEventTimingAC, // event occurs [duration] before a meal (from the Latin ante cibus).
    kEventTimingACM, // event occurs [duration] before breakfast (from the Latin ante cibus matutinus).
    kEventTimingACD, // event occurs [duration] before lunch (from the Latin ante cibus diurnus).
    kEventTimingACV, // event occurs [duration] before dinner (from the Latin ante cibus vespertinus).
    kEventTimingPC, // event occurs [duration] after a meal (from the Latin post cibus).
    kEventTimingPCM, // event occurs [duration] after breakfast (from the Latin post cibus matutinus).
    kEventTimingPCD, // event occurs [duration] after lunch (from the Latin post cibus diurnus).
    kEventTimingPCV, // event occurs [duration] after dinner (from the Latin post cibus vespertinus).
} kEventTiming;

/*
 * When the event occurs
 */
@property (nonatomic, strong) NSArray/*<Period>*/ *event_;

/*
 * Only if there is none or one event
 */
@property (nonatomic, strong) FHIRScheduleRepeatComponent *repeat;

- (FHIRErrorList *)validate;

@end
