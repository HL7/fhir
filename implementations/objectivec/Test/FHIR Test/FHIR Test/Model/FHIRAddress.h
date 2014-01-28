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
 * A postal address
 *
 * [FhirComposite("Address")]
 * [Serializable]
 */

#import "FHIRElement.h"


@class FHIRCode;
@class FHIRString;
@class FHIRPeriod;

@interface FHIRAddress : FHIRElement

/*
 * The use of an address
 */
typedef enum 
{
    kAddressUseHome, // A communication address at a home.
    kAddressUseWork, // An office address. First choice for business related contacts during business hours.
    kAddressUseTemp, // A temporary address. The period can provide more detailed information.
    kAddressUseOld, // This address is no longer in use (or was never correct, but retained for records).
} kAddressUse;

/*
 * home | work | temp | old - purpose of this address
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *useElement;

@property (nonatomic) kAddressUse use;

/*
 * Text representation of the address
 */
@property (nonatomic, strong) FHIRString *textElement;

@property (nonatomic, strong) NSString *text;

/*
 * Street name, number, direction & P.O. Box etc
 */
@property (nonatomic, strong) NSArray/*<string>*/ *lineElement;

@property (nonatomic, strong) NSArray /*<NSString>*/ *line;

/*
 * Name of city, town etc.
 */
@property (nonatomic, strong) FHIRString *cityElement;

@property (nonatomic, strong) NSString *city;

/*
 * Sub-unit of country (abreviations ok)
 */
@property (nonatomic, strong) FHIRString *stateElement;

@property (nonatomic, strong) NSString *state;

/*
 * Postal code for area
 */
@property (nonatomic, strong) FHIRString *zipElement;

@property (nonatomic, strong) NSString *zip;

/*
 * Country (can be ISO 3166 3 letter code)
 */
@property (nonatomic, strong) FHIRString *countryElement;

@property (nonatomic, strong) NSString *country;

/*
 * Time period when address was/is in use
 */
@property (nonatomic, strong) FHIRPeriod *period;

- (FHIRErrorList *)validate;

@end
