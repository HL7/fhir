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
  

 * Generated on Wed, Jan 29, 2014 07:56+1100 for FHIR v0.12
 */
/*
 * null
 *
 * [FhirComposite("DiagnosticOrderItemComponent")]
 * [Serializable]
 */

#import "FHIRElement.h"

#import "FHIRDiagnosticOrder.h"

@class FHIRCodeableConcept;
@class FHIRResourceReference;
@class FHIRCode;
@class FHIRDiagnosticOrderEventComponent;

@interface FHIRDiagnosticOrderItemComponent : FHIRElement

/*
 * Code to indicate the item (test or panel) being ordered
 */
@property (nonatomic, strong) FHIRCodeableConcept *code;

/*
 * If this item relates to specific specimens
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *specimen;

/*
 * Location of requested test (if applicable)
 */
@property (nonatomic, strong) FHIRCodeableConcept *bodySite;

/*
 * requested | received | accepted | in progress | review | completed | suspended | rejected | failed
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kDiagnosticOrderStatus status;

/*
 * Events specific to this item
 */
@property (nonatomic, strong) NSArray/*<DiagnosticOrderEventComponent>*/ *event_;

- (FHIRErrorList *)validate;

@end
