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
 * A description of a query with a set of parameters
 *
 * [FhirResource("Query")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRUri;
@class FHIRExtension;
@class FHIRQueryResponseComponent;

@interface FHIRQuery : FHIRResource

/*
 * The outcome of processing a query request
 */
typedef enum 
{
    kQueryOutcomeOk, // The query was processed successfully.
    kQueryOutcomeLimited, // The query was processed successfully, but some additional limitations were added.
    kQueryOutcomeRefused, // The server refused to process the query.
    kQueryOutcomeError, // The server tried to process the query, but some error occurred.
} kQueryOutcome;

/*
 * Links query and its response(s)
 */
@property (nonatomic, strong) FHIRUri *identifierElement;

@property (nonatomic, strong) NSString *identifier;

/*
 * Set of query parameters with values
 */
@property (nonatomic, strong) NSArray/*<Extension>*/ *parameter;

/*
 * If this is a response to a query
 */
@property (nonatomic, strong) FHIRQueryResponseComponent *response;

- (FHIRErrorList *)validate;

@end
