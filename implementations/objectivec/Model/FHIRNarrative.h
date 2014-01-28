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
 * A human-readable formatted text, including images
 *
 * [FhirComposite("Narrative")]
 * [Serializable]
 */

#import "FHIRElement.h"


@class FHIRCode;
@class FHIRXhtml;

@interface FHIRNarrative : FHIRElement

/*
 * The status of a resource narrative
 */
typedef enum 
{
    kNarrativeStatusGenerated, // The contents of the narrative are entirely generated from the structured data in the resource.
    kNarrativeStatusExtensions, // The contents of the narrative are entirely generated from the structured data in the resource and some of the content is generated from extensions.
    kNarrativeStatusAdditional, // The contents of the narrative contain additional information not found in the structured data.
    kNarrativeStatusEmpty, // the contents of the narrative are some equivalent of "No human-readable text provided for this resource".
} kNarrativeStatus;

/*
 * generated | extensions | additional
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kNarrativeStatus status;

/*
 * Limited xhtml content
 */
@property (nonatomic, strong) FHIRXhtml *divElement;

@property (nonatomic, strong) NSString *div;

- (FHIRErrorList *)validate;

@end
