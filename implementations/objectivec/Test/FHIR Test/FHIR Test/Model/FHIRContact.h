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
 * Technology mediated contact details (phone, fax, email, etc)
 *
 * [FhirComposite("Contact")]
 * [Serializable]
 */

#import "FHIRElement.h"


@class FHIRCode;
@class FHIRString;
@class FHIRPeriod;

@interface FHIRContact : FHIRElement

/*
 * Telecommunications form for contact
 */
typedef enum 
{
    kContactSystemPhone, // The value is a telephone number used for voice calls. Use of full international numbers starting with + is recommended to enable automatic dialing support but not required.
    kContactSystemFax, // The value is a fax machine. Use of full international numbers starting with + is recommended to enable automatic dialing support but not required.
    kContactSystemEmail, // The value is an email address.
    kContactSystemUrl, // The value is a url. This is intended for various personal contacts including blogs, Twitter, Facebook, etc. Do not use for email addresses.
} kContactSystem;

/*
 * Location, type or status of telecommunications address indicating use
 */
typedef enum 
{
    kContactUseHome, // A communication contact at a home; attempted contacts for business purposes might intrude privacy and chances are one will contact family or other household members instead of the person one wishes to call. Typically used with urgent cases, or if no other contacts are available.
    kContactUseWork, // An office contact. First choice for business related contacts during business hours.
    kContactUseTemp, // A temporary contact. The period can provide more detailed information.
    kContactUseOld, // This contact is no longer in use (or was never correct, but retained for records).
    kContactUseMobile, // A telecommunication device that moves and stays with its owner. May have characteristics of all other use codes, suitable for urgent matters, not the first choice for routine business.
} kContactUse;

/*
 * phone | fax | email | url
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *systemElement;

@property (nonatomic) kContactSystem system;

/*
 * The actual contact details
 */
@property (nonatomic, strong) FHIRString *valueElement;

@property (nonatomic, strong) NSString *value;

/*
 * home | work | temp | old | mobile - purpose of this address
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *useElement;

@property (nonatomic) kContactUse use;

/*
 * Time period when the contact was/is in use
 */
@property (nonatomic, strong) FHIRPeriod *period;

- (FHIRErrorList *)validate;

@end
