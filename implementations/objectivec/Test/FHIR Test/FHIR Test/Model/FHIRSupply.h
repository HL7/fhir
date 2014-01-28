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
 * A supply -  request and provision
 *
 * [FhirResource("Supply")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRCodeableConcept;
@class FHIRIdentifier;
@class FHIRCode;
@class FHIRResourceReference;
@class FHIRSupplyDispenseComponent;

@interface FHIRSupply : FHIRResource

/*
 * Status of the dispense
 */
typedef enum 
{
    kSupplyDispenseStatusInProgress, // Supply has been requested, but not dispensed.
    kSupplyDispenseStatusDispensed, // Supply is part of a pharmacy order and has been dispensed.
    kSupplyDispenseStatusAbandoned, // Dispensing was not completed.
} kSupplyDispenseStatus;

/*
 * Status of the supply
 */
typedef enum 
{
    kSupplyStatusRequested, // Supply has been requested, but not dispensed.
    kSupplyStatusDispensed, // Supply is part of a pharmacy order and has been dispensed.
    kSupplyStatusReceived, // Supply has been received by the requestor.
    kSupplyStatusFailed, // The supply will not be completed because the supplier was unable or unwilling to supply the item.
    kSupplyStatusCancelled, // The orderer of the supply cancelled the request.
} kSupplyStatus;

/*
 * The kind of supply (central, non-stock, etc)
 */
@property (nonatomic, strong) FHIRCodeableConcept *kind;

/*
 * Unique identifier
 */
@property (nonatomic, strong) FHIRIdentifier *identifier;

/*
 * requested | dispensed | received | failed | cancelled
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kSupplyStatus status;

/*
 * Medication, Substance, or Device requested to be supplied
 */
@property (nonatomic, strong) FHIRResourceReference *orderedItem;

/*
 * Patient for whom the item is supplied
 */
@property (nonatomic, strong) FHIRResourceReference *patient;

/*
 * Supply details
 */
@property (nonatomic, strong) NSArray/*<SupplyDispenseComponent>*/ *dispense;

- (FHIRErrorList *)validate;

@end
