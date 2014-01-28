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
 * A Diagnostic report - a combination of request information, atomic results, images, interpretation, and formatted reports
 */
#import "FHIRDiagnosticReport.h"

#import "FHIRCode.h"
#import "FHIRDateTime.h"
#import "FHIRResourceReference.h"
#import "FHIRIdentifier.h"
#import "FHIRCodeableConcept.h"
#import "FHIRElement.h"
#import "FHIRResultGroupComponent.h"
#import "FHIRDiagnosticReportImageComponent.h"
#import "FHIRString.h"
#import "FHIRAttachment.h"

#import "FHIRErrorList.h"

@implementation FHIRDiagnosticReport

- (kDiagnosticReportStatus )status
{
    return [FHIREnumHelper parseString:[self.statusElement value] enumType:kEnumTypeDiagnosticReportStatus];
}

- (void )setStatus:(kDiagnosticReportStatus )status
{
    [self setStatusElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:status enumType:kEnumTypeDiagnosticReportStatus]]];
}


- (NSString *)issued
{
    if(self.issuedElement)
    {
        return [self.issuedElement value];
    }
    return nil;
}

- (void )setIssued:(NSString *)issued
{
    if(issued)
    {
        [self setIssuedElement:[[FHIRDateTime alloc] initWithValue:issued]];
    }
    else
    {
        [self setIssuedElement:nil];
    }
}


- (NSString *)conclusion
{
    if(self.conclusionElement)
    {
        return [self.conclusionElement value];
    }
    return nil;
}

- (void )setConclusion:(NSString *)conclusion
{
    if(conclusion)
    {
        [self setConclusionElement:[[FHIRString alloc] initWithValue:conclusion]];
    }
    else
    {
        [self setConclusionElement:nil];
    }
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.statusElement != nil )
        [result addValidationRange:[self.statusElement validate]];
    if(self.issuedElement != nil )
        [result addValidationRange:[self.issuedElement validate]];
    if(self.subject != nil )
        [result addValidationRange:[self.subject validate]];
    if(self.performer != nil )
        [result addValidationRange:[self.performer validate]];
    if(self.identifier != nil )
        [result addValidationRange:[self.identifier validate]];
    if(self.requestDetail != nil )
        for(FHIRResourceReference *elem in self.requestDetail)
            [result addValidationRange:[elem validate]];
    if(self.serviceCategory != nil )
        [result addValidationRange:[self.serviceCategory validate]];
    if(self.diagnostic != nil )
        [result addValidationRange:[self.diagnostic validate]];
    if(self.results != nil )
        [result addValidationRange:[self.results validate]];
    if(self.imagingStudy != nil )
        for(FHIRResourceReference *elem in self.imagingStudy)
            [result addValidationRange:[elem validate]];
    if(self.image != nil )
        for(FHIRDiagnosticReportImageComponent *elem in self.image)
            [result addValidationRange:[elem validate]];
    if(self.conclusionElement != nil )
        [result addValidationRange:[self.conclusionElement validate]];
    if(self.codedDiagnosis != nil )
        for(FHIRCodeableConcept *elem in self.codedDiagnosis)
            [result addValidationRange:[elem validate]];
    if(self.presentedForm != nil )
        for(FHIRAttachment *elem in self.presentedForm)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
