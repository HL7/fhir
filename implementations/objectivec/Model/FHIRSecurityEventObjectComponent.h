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
 * [FhirComposite("SecurityEventObjectComponent")]
 * [Serializable]
 */

#import "FHIRElement.h"

#import "FHIRSecurityEvent.h"

@class FHIRIdentifier;
@class FHIRResourceReference;
@class FHIRCode;
@class FHIRCodeableConcept;
@class FHIRString;
@class FHIRBase64Binary;
@class FHIRSecurityEventObjectDetailComponent;

@interface FHIRSecurityEventObjectComponent : FHIRElement

/*
 * Specific instance of object (e.g. versioned)
 */
@property (nonatomic, strong) FHIRIdentifier *identifier;

/*
 * Specific instance of resource (e.g. versioned)
 */
@property (nonatomic, strong) FHIRResourceReference *reference;

/*
 * Object type being audited
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *typeElement;

@property (nonatomic) kSecurityEventObjectType type;

/*
 * Functional application role of Object
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *roleElement;

@property (nonatomic) kSecurityEventObjectRole role;

/*
 * Life-cycle stage for the object
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *lifecycleElement;

@property (nonatomic) kSecurityEventObjectLifecycle lifecycle;

/*
 * Policy-defined sensitivity for the object
 */
@property (nonatomic, strong) FHIRCodeableConcept *sensitivity;

/*
 * Instance-specific descriptor for Object
 */
@property (nonatomic, strong) FHIRString *nameElement;

@property (nonatomic, strong) NSString *name;

/*
 * Descriptive text
 */
@property (nonatomic, strong) FHIRString *descriptionElement;

@property (nonatomic, strong) NSString *description;

/*
 * Actual query for object
 */
@property (nonatomic, strong) FHIRBase64Binary *queryElement;

@property (nonatomic, strong) NSData *query;

/*
 * Additional Information about the Object
 */
@property (nonatomic, strong) NSArray/*<SecurityEventObjectDetailComponent>*/ *detail;

- (FHIRErrorList *)validate;

@end
