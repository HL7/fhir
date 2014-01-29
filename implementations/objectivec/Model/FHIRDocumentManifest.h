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
 * A manifest that defines a set of documents
 *
 * [FhirResource("DocumentManifest")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRResourceReference;
@class FHIRCodeableConcept;
@class FHIRDateTime;
@class FHIRUri;
@class FHIRCode;
@class FHIRString;

@interface FHIRDocumentManifest : FHIRResource

/*
 * Unique Identifier for the set of documents
 */
@property (nonatomic, strong) FHIRIdentifier *masterIdentifier;

/*
 * Other identifiers for the manifest
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * The subject of the set of documents
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *subject;

/*
 * Intended to get notified about this set of documents
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *recipient;

/*
 * What kind of document set this is
 */
@property (nonatomic, strong) FHIRCodeableConcept *type;

/*
 * Who and/or what authored the document
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *author;

/*
 * When this document manifest created
 */
@property (nonatomic, strong) FHIRDateTime *createdElement;

@property (nonatomic, strong) NSString *created;

/*
 * The source system/application/software
 */
@property (nonatomic, strong) FHIRUri *sourceElement;

@property (nonatomic, strong) NSString *source;

/*
 * current | superceded | entered in error
 */
@property (nonatomic, strong) FHIRCode *statusElement;

@property (nonatomic, strong) NSString *status;

/*
 * If this document manifest replaces another
 */
@property (nonatomic, strong) FHIRResourceReference *supercedes;

/*
 * Human-readable description (title)
 */
@property (nonatomic, strong) FHIRString *descriptionElement;

@property (nonatomic, strong) NSString *description;

/*
 * Sensitivity of set of documents
 */
@property (nonatomic, strong) FHIRCodeableConcept *confidentiality;

/*
 * Contents of this set of documents
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *content;

- (FHIRErrorList *)validate;

@end
