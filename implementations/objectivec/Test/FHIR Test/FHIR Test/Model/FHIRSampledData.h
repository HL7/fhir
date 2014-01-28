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
 * A series of measurements taken by a device
 *
 * [FhirComposite("SampledData")]
 * [Serializable]
 */

#import "FHIRElement.h"


@class FHIRQuantity;
@class FHIRDecimal;
@class FHIRInteger;
@class FHIRString;

@interface FHIRSampledData : FHIRElement

/*
 * Zero value and units
 */
@property (nonatomic, strong) FHIRQuantity *origin;

/*
 * Number of milliseconds between samples
 */
@property (nonatomic, strong) FHIRDecimal *periodElement;

@property (nonatomic, strong) NSDecimalNumber *period;

/*
 * Multiply data by this before adding to origin
 */
@property (nonatomic, strong) FHIRDecimal *factorElement;

@property (nonatomic, strong) NSDecimalNumber *factor;

/*
 * Lower limit of detection
 */
@property (nonatomic, strong) FHIRDecimal *lowerLimitElement;

@property (nonatomic, strong) NSDecimalNumber *lowerLimit;

/*
 * Upper limit of detection
 */
@property (nonatomic, strong) FHIRDecimal *upperLimitElement;

@property (nonatomic, strong) NSDecimalNumber *upperLimit;

/*
 * Number of sample points at each time point
 */
@property (nonatomic, strong) FHIRInteger *dimensionsElement;

@property (nonatomic, strong) NSNumber *dimensions;

/*
 * Decimal values with spaces, or "E" | "U" | "L"
 */
@property (nonatomic, strong) FHIRString *dataElement;

@property (nonatomic, strong) NSString *data;

- (FHIRErrorList *)validate;

@end
