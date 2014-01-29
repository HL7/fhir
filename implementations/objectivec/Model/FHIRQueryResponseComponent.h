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
 * null
 *
 * [FhirComposite("QueryResponseComponent")]
 * [Serializable]
 */

#import "FHIRElement.h"

#import "FHIRQuery.h"

@class FHIRUri;
@class FHIRCode;
@class FHIRInteger;
@class FHIRExtension;
@class FHIRResourceReference;

@interface FHIRQueryResponseComponent : FHIRElement

/*
 * Links response to source query
 */
@property (nonatomic, strong) FHIRUri *identifierElement;

@property (nonatomic, strong) NSString *identifier;

/*
 * ok | limited | refused | error
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *outcomeElement;

@property (nonatomic) kQueryOutcome outcome;

/*
 * Total number of matching records
 */
@property (nonatomic, strong) FHIRInteger *totalElement;

@property (nonatomic, strong) NSNumber *total;

/*
 * Parameters server used
 */
@property (nonatomic, strong) NSArray/*<Extension>*/ *parameter;

/*
 * To get first page (if paged)
 */
@property (nonatomic, strong) NSArray/*<Extension>*/ *first;

/*
 * To get previous page (if paged)
 */
@property (nonatomic, strong) NSArray/*<Extension>*/ *previous;

/*
 * To get next page (if paged)
 */
@property (nonatomic, strong) NSArray/*<Extension>*/ *next;

/*
 * To get last page (if paged)
 */
@property (nonatomic, strong) NSArray/*<Extension>*/ *last;

/*
 * Resources that are the results of the search
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *reference;

- (FHIRErrorList *)validate;

@end
