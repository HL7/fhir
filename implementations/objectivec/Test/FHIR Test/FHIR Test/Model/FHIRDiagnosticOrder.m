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
 * A request for a diagnostic service
 */
#import "FHIRDiagnosticOrder.h"

#import "FHIRResourceReference.h"
#import "FHIRIdentifier.h"
#import "FHIRString.h"
#import "FHIRCode.h"
#import "FHIRDiagnosticOrderEventComponent.h"
#import "FHIRDiagnosticOrderItemComponent.h"

#import "FHIRErrorList.h"

@implementation FHIRDiagnosticOrder

- (NSString *)clinicalNotes
{
    if(self.clinicalNotesElement)
    {
        return [self.clinicalNotesElement value];
    }
    return nil;
}

- (void )setClinicalNotes:(NSString *)clinicalNotes
{
    if(clinicalNotes)
    {
        [self setClinicalNotesElement:[[FHIRString alloc] initWithValue:clinicalNotes]];
    }
    else
    {
        [self setClinicalNotesElement:nil];
    }
}


- (kDiagnosticOrderStatus )status
{
    return [FHIREnumHelper parseString:[self.statusElement value] enumType:kEnumTypeDiagnosticOrderStatus];
}

- (void )setStatus:(kDiagnosticOrderStatus )status
{
    [self setStatusElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:status enumType:kEnumTypeDiagnosticOrderStatus]]];
}


- (kDiagnosticOrderPriority )priority
{
    return [FHIREnumHelper parseString:[self.priorityElement value] enumType:kEnumTypeDiagnosticOrderPriority];
}

- (void )setPriority:(kDiagnosticOrderPriority )priority
{
    [self setPriorityElement:[[FHIRCode/*<code>*/ alloc] initWithValue:[FHIREnumHelper enumToString:priority enumType:kEnumTypeDiagnosticOrderPriority]]];
}


- (FHIRErrorList *)validate
{
    FHIRErrorList *result = [[FHIRErrorList alloc] init];
    
    [result addValidation:[super validate]];
    
    if(self.subject != nil )
        [result addValidationRange:[self.subject validate]];
    if(self.orderer != nil )
        [result addValidationRange:[self.orderer validate]];
    if(self.identifier != nil )
        for(FHIRIdentifier *elem in self.identifier)
            [result addValidationRange:[elem validate]];
    if(self.encounter != nil )
        [result addValidationRange:[self.encounter validate]];
    if(self.clinicalNotesElement != nil )
        [result addValidationRange:[self.clinicalNotesElement validate]];
    if(self.specimen != nil )
        for(FHIRResourceReference *elem in self.specimen)
            [result addValidationRange:[elem validate]];
    if(self.statusElement != nil )
        [result addValidationRange:[self.statusElement validate]];
    if(self.priorityElement != nil )
        [result addValidationRange:[self.priorityElement validate]];
    if(self.event_ != nil )
        for(FHIRDiagnosticOrderEventComponent *elem in self.event_)
            [result addValidationRange:[elem validate]];
    if(self.item != nil )
        for(FHIRDiagnosticOrderItemComponent *elem in self.item)
            [result addValidationRange:[elem validate]];
    
    return result;
}

@end
