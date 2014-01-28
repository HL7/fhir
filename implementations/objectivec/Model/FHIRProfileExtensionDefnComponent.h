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
 * [FhirComposite("ProfileExtensionDefnComponent")]
 * [Serializable]
 */

#import "FHIRElement.h"

#import "FHIRProfile.h"

@class FHIRCode;
@class FHIRString;
@class FHIRElementDefinitionComponent;

@interface FHIRProfileExtensionDefnComponent : FHIRElement

/*
 * Identifies the extension in this profile
 */
@property (nonatomic, strong) FHIRCode *codeElement;

@property (nonatomic, strong) NSString *code;

/*
 * Use this name when displaying the value
 */
@property (nonatomic, strong) FHIRString *displayElement;

@property (nonatomic, strong) NSString *display;

/*
 * resource | datatype | mapping | extension
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *contextTypeElement;

@property (nonatomic) kExtensionContext contextType;

/*
 * Where the extension can be used in instances
 */
@property (nonatomic, strong) NSArray/*<string>*/ *contextElement;

@property (nonatomic, strong) NSArray /*<NSString>*/ *context;

/*
 * Definition of the extension and its content
 */
@property (nonatomic, strong) FHIRElementDefinitionComponent *definition;

- (FHIRErrorList *)validate;

@end
