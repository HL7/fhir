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
 * An identifier intended for computation
 *
 * [FhirComposite("Identifier")]
 * [Serializable]
 */

#import "FHIRElement.h"


@class FHIRCode;
@class FHIRString;
@class FHIRUri;
@class FHIRPeriod;
@class FHIRResourceReference;

@interface FHIRIdentifier : FHIRElement

/*
 * Identifies the use for this identifier, if known
 */
typedef enum 
{
    kIdentifierUseUsual, // the identifier recommended for display and use in real-world interactions.
    kIdentifierUseOfficial, // the identifier considered to be most trusted for the identification of this item.
    kIdentifierUseTemp, // A temporary identifier.
    kIdentifierUseSecondary, // An identifier that was assigned in secondary use - it serves to identify the object in a relative context, but cannot be consistently assigned to the same object again in a different context.
} kIdentifierUse;

/*
 * usual | official | temp | secondary (If known)
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *useElement;

@property (nonatomic) kIdentifierUse use;

/*
 * Description of identifier
 */
@property (nonatomic, strong) FHIRString *labelElement;

@property (nonatomic, strong) NSString *label;

/*
 * The namespace for the identifier
 */
@property (nonatomic, strong) FHIRUri *systemElement;

@property (nonatomic, strong) NSString *system;

/*
 * The value that is unique
 */
@property (nonatomic, strong) FHIRString *valueElement;

@property (nonatomic, strong) NSString *value;

/*
 * Time period when id was valid for use
 */
@property (nonatomic, strong) FHIRPeriod *period;

/*
 * Organization that issued id (may be just text)
 */
@property (nonatomic, strong) FHIRResourceReference *assigner;

- (FHIRErrorList *)validate;

@end
