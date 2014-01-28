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
 * null
 *
 * [FhirComposite("QuestionComponent")]
 * [Serializable]
 */

#import "FHIRElement.h"

#import "FHIRQuestionnaire.h"

@class FHIRCodeableConcept;
@class FHIRString;
@class FHIRElement;
@class FHIRCoding;
@class FHIRResourceReference;
@class FHIRGroupComponent;

@interface FHIRQuestionComponent : FHIRElement

/*
 * Code or name of the question
 */
@property (nonatomic, strong) FHIRCodeableConcept *name;

/*
 * Text of the question as it is shown to the user
 */
@property (nonatomic, strong) FHIRString *textElement;

@property (nonatomic, strong) NSString *text;

/*
 * Single-valued answer to the question
 */
@property (nonatomic, strong) FHIRElement *answer;

/*
 * Selected options
 */
@property (nonatomic, strong) NSArray/*<Coding>*/ *choice;

/*
 * Valueset containing the possible options
 */
@property (nonatomic, strong) FHIRResourceReference *options;

/*
 * Structured answer
 */
@property (nonatomic, strong) FHIRElement *data;

/*
 * Remarks about the answer given
 */
@property (nonatomic, strong) FHIRString *remarksElement;

@property (nonatomic, strong) NSString *remarks;

/*
 * Nested questionnaire group
 */
@property (nonatomic, strong) NSArray/*<GroupComponent>*/ *group;

- (FHIRErrorList *)validate;

@end
