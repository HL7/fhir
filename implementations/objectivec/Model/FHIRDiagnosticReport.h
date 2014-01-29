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
 * A Diagnostic report - a combination of request information, atomic results, images, interpretation, as well as formatted reports
 *
 * [FhirResource("DiagnosticReport")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRCode;
@class FHIRDateTime;
@class FHIRResourceReference;
@class FHIRIdentifier;
@class FHIRCodeableConcept;
@class FHIRElement;
@class FHIRResultGroupComponent;
@class FHIRDiagnosticReportImageComponent;
@class FHIRString;
@class FHIRAttachment;

@interface FHIRDiagnosticReport : FHIRResource

/*
 * The status of the diagnostic report as a whole
 */
typedef enum 
{
    kDiagnosticReportStatusRegistered, // The existence of the report is registered, but there is nothing yet available.
    kDiagnosticReportStatusPartial, // This is a partial (e.g. initial, interim or preliminary) report: data in the report may be incomplete or unverified.
    kDiagnosticReportStatusFinal, // The report is complete and verified by an authorized person.
    kDiagnosticReportStatusCorrected, // The report has been modified subsequent to being Final, and is complete and verified by an authorized person.
    kDiagnosticReportStatusAmended, // The report has been modified subsequent to being Final, and is complete and verified by an authorized person, and data has been changed.
    kDiagnosticReportStatusAppended, // The report has been modified subsequent to being Final, and is complete and verified by an authorized person. New content has been added, but existing content hasn't changed.
    kDiagnosticReportStatusCancelled, // The report is unavailable because the measurement was not started or not completed (also sometimes called "aborted").
    kDiagnosticReportStatusEnteredInError, // The report has been withdrawn following previous Final release.
} kDiagnosticReportStatus;

/*
 * registered | partial | final | corrected +
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kDiagnosticReportStatus status;

/*
 * Date this version was released
 */
@property (nonatomic, strong) FHIRDateTime *issuedElement;

@property (nonatomic, strong) NSString *issued;

/*
 * The subject of the report, usually, but not always, the patient
 */
@property (nonatomic, strong) FHIRResourceReference *subject;

/*
 * Responsible Diagnostic Service
 */
@property (nonatomic, strong) FHIRResourceReference *performer;

/*
 * Id for external references to this report
 */
@property (nonatomic, strong) FHIRIdentifier *identifier;

/*
 * What was requested
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *requestDetail;

/*
 * Biochemistry, Hematology etc.
 */
@property (nonatomic, strong) FHIRCodeableConcept *serviceCategory;

/*
 * Physiologically Relevant time/time-period for report
 */
@property (nonatomic, strong) FHIRElement *diagnostic;

/*
 * Results grouped by specimen/kind/category
 */
@property (nonatomic, strong) FHIRResultGroupComponent *results;

/*
 * Reference to full details of imaging associated with the diagnostic report
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *imagingStudy;

/*
 * Key images associated with this report
 */
@property (nonatomic, strong) NSArray/*<DiagnosticReportImageComponent>*/ *image;

/*
 * Clinical Interpretation of test results
 */
@property (nonatomic, strong) FHIRString *conclusionElement;

@property (nonatomic, strong) NSString *conclusion;

/*
 * Codes for the conclusion
 */
@property (nonatomic, strong) NSArray/*<CodeableConcept>*/ *codedDiagnosis;

/*
 * Entire Report as issued
 */
@property (nonatomic, strong) NSArray/*<Attachment>*/ *presentedForm;

- (FHIRErrorList *)validate;

@end
