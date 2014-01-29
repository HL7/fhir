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
 * Information summarized from a list of other resources
 *
 * [FhirResource("List")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRCodeableConcept;
@class FHIRResourceReference;
@class FHIRDateTime;
@class FHIRBoolean;
@class FHIRCode;
@class FHIRListEntryComponent;

@interface FHIRList : FHIRResource

/*
 * The processing mode that applies to this list
 */
typedef enum 
{
    kListModeWorking, // This list is the master list, maintained in an ongoing fashion with regular updates as the real world list it is tracking changes.
    kListModeSnapshot, // This list was prepared as a snapshot. It should not be assumed to be current.
    kListModeChanges, // The list is prepared as a statement of changes that have been made or recommended.
} kListMode;

/*
 * Business identifier
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * What the purpose of this list is
 */
@property (nonatomic, strong) FHIRCodeableConcept *code;

/*
 * If all resources have the same subject
 */
@property (nonatomic, strong) FHIRResourceReference *subject;

/*
 * Who and/or what defined the list contents
 */
@property (nonatomic, strong) FHIRResourceReference *source;

/*
 * When the list was prepared
 */
@property (nonatomic, strong) FHIRDateTime *dateElement;

@property (nonatomic, strong) NSString *date;

/*
 * Whether items in the list have a meaningful order
 */
@property (nonatomic, strong) FHIRBoolean *orderedElement;

@property (nonatomic, strong) NSNumber *ordered;

/*
 * working | snapshot | changes
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *modeElement;

@property (nonatomic) kListMode mode;

/*
 * Entries in the list
 */
@property (nonatomic, strong) NSArray/*<ListEntryComponent>*/ *entry;

/*
 * Why list is empty
 */
@property (nonatomic, strong) FHIRCodeableConcept *emptyReason;

- (FHIRErrorList *)validate;

@end
