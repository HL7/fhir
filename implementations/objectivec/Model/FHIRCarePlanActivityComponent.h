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
 * [FhirComposite("CarePlanActivityComponent")]
 * [Serializable]
 */

#import "FHIRElement.h"

#import "FHIRCarePlan.h"

@class FHIRIdref;
@class FHIRCode;
@class FHIRBoolean;
@class FHIRResourceReference;
@class FHIRString;
@class FHIRCarePlanActivitySimpleComponent;

@interface FHIRCarePlanActivityComponent : FHIRElement

/*
 * Goals this activity relates to
 */
@property (nonatomic, strong) NSArray/*<idref>*/ *goalElement;

@property (nonatomic, strong) NSArray /*<NSString>*/ *goal;

/*
 * not started | scheduled | in progress | on hold | completed | cancelled
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kCarePlanActivityStatus status;

/*
 * Do NOT do
 */
@property (nonatomic, strong) FHIRBoolean *prohibitedElement;

@property (nonatomic, strong) NSNumber *prohibited;

/*
 * Appointments, orders, etc.
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *actionResulting;

/*
 * Comments about the activity
 */
@property (nonatomic, strong) FHIRString *notesElement;

@property (nonatomic, strong) NSString *notes;

/*
 * Activity details defined in specific resource
 */
@property (nonatomic, strong) FHIRResourceReference *detail;

/*
 * Activity details summarised here
 */
@property (nonatomic, strong) FHIRCarePlanActivitySimpleComponent *simple;

- (FHIRErrorList *)validate;

@end
