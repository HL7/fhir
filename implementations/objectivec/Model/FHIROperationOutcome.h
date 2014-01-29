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
 * Information about the success/failure of an action
 *
 * [FhirResource("OperationOutcome")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIROperationOutcomeIssueComponent;

@interface FHIROperationOutcome : FHIRResource

/*
 * A coded expression of the type of issue
 */
typedef enum 
{
    kIssueTypeInvalid, // Content invalid against Specification or Profile.
    kIssueTypeStructure, // content structural issue.
    kIssueTypeRequired, // required element missing.
    kIssueTypeValue, // element value invalid.
    kIssueTypeInvariant, // schematron rule.
    kIssueTypeSecurity, // authorization/permissions issue.
    kIssueTypeLogin, // the client needs to initiate the authentication process ().
    kIssueTypeUnknown, // user/system not able to be authenticated.
    kIssueTypeExpired, // user session expired.
    kIssueTypeForbidden, // user rights failure.
    kIssueTypeProcessing, // processing issues.
    kIssueTypeNotSupported, // resource not supported.
    kIssueTypeDuplicate, // duplicate resource.
    kIssueTypeNotFound, // reference not found.
    kIssueTypeTooLong, // existing content too long.
    kIssueTypeCodeUnknown, // code could not be understood.
    kIssueTypeExtension, // extension not recognized.
    kIssueTypeTooCostly, // operation denied to protect server resources.
    kIssueTypeBusinessRule, // content failed to pass some business rule.
    kIssueTypeConflict, // content could not be accepted because of an edit conflict (i.e. version aware updates).
    kIssueTypeTransient, // transient processing issues.
    kIssueTypeLockError, // resource/record locking failure.
    kIssueTypeNoStore, // persistent store unavailable.
    kIssueTypeException, // unexpected internal error.
    kIssueTypeTimeout, // internal timeout.
    kIssueTypeThrottled, // The system is not prepared to handle this request due to load management.
} kIssueType;

/*
 * How the issue affects the success of the action
 */
typedef enum 
{
    kIssueSeverityFatal, // The issue caused the action to fail, and no further checking could be performed.
    kIssueSeverityError, // The issue is sufficiently important to cause the action to fail.
    kIssueSeverityWarning, // The issue is not important enough to cause the action to fail, but may cause it to be performed suboptimally or in a way that is not as desired.
    kIssueSeverityInformation, // The issue has no relation to the degree of success of the action.
} kIssueSeverity;

/*
 * A single issue associated with the action
 */
@property (nonatomic, strong) NSArray/*<OperationOutcomeIssueComponent>*/ *issue;

- (FHIRErrorList *)validate;

@end
