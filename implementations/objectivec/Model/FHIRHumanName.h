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
 * Name of a human - parts and usage
 *
 * [FhirComposite("HumanName")]
 * [Serializable]
 */

#import "FHIRElement.h"


@class FHIRCode;
@class FHIRString;
@class FHIRPeriod;

@interface FHIRHumanName : FHIRElement

/*
 * The use of a human name
 */
typedef enum 
{
    kNameUseUsual, // Known as/conventional/the one you normally use.
    kNameUseOfficial, // The formal name as registered in an official (government) registry, but which name might not be commonly used. May be called "legal name".
    kNameUseTemp, // A temporary name. Name.period can provide more detailed information. This may also be used for temporary names assigned at birth or in emergency situations.
    kNameUseNickname, // A name that is used to address the person in an informal manner, but is not part of their formal or usual name.
    kNameUseAnonymous, // Anonymous assigned name, alias, or pseudonym (used to protect a person's identity for privacy reasons).
    kNameUseOld, // This name is no longer in use (or was never correct, but retained for records).
    kNameUseMaiden, // A name used prior to marriage. Marriage naming customs vary greatly around the world. This name use is for use by applications that collect and store "maiden" names. Though the concept of maiden name is often gender specific, the use of this term is not gender specific. The use of this term does not imply any particular history for a person's name, nor should the maiden name be determined algorithmically.
} kNameUse;

/*
 * usual | official | temp | nickname | anonymous | old | maiden
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *useElement;

@property (nonatomic) kNameUse use;

/*
 * Text representation of the full name
 */
@property (nonatomic, strong) FHIRString *textElement;

@property (nonatomic, strong) NSString *text;

/*
 * Family name (often called 'Surname')
 */
@property (nonatomic, strong) NSArray/*<string>*/ *familyElement;

@property (nonatomic, strong) NSArray /*<NSString>*/ *family;

/*
 * Given names (not always 'first'). Includes middle names
 */
@property (nonatomic, strong) NSArray/*<string>*/ *givenElement;

@property (nonatomic, strong) NSArray /*<NSString>*/ *given;

/*
 * Parts that come before the name
 */
@property (nonatomic, strong) NSArray/*<string>*/ *prefixElement;

@property (nonatomic, strong) NSArray /*<NSString>*/ *prefix;

/*
 * Parts that come after the name
 */
@property (nonatomic, strong) NSArray/*<string>*/ *suffixElement;

@property (nonatomic, strong) NSArray /*<NSString>*/ *suffix;

/*
 * Time period when name was/is in use
 */
@property (nonatomic, strong) FHIRPeriod *period;

- (FHIRErrorList *)validate;

@end
