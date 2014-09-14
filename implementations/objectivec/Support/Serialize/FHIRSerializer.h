/*
 Copyright (c) 2011+, HL7, Inc.
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
 
 */

//
//  FHIRSerializer.h
//  FhirTest
//
//  Created by Andrew on 2013-10-23.
//  Copyright (c) 2013 Ideaworks. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "FHIRWriterProtocol.h"

@interface FHIRSerializer : NSObject<FHIRWriterProtocol>

- (id)initWithFhirObject:(id)fhirObject;
- (id)initWithFhirObject:(id)fhirObject andTabValue:(int)tabValue;

@property (nonatomic, strong) id fhirObject;
@property (nonatomic, strong) NSString *fhirOcType;
@property (nonatomic, strong) NSMutableDictionary *serializedObject;
@property (nonatomic, strong) NSMutableString *serializedString;
@property (nonatomic, strong) NSMutableDictionary *serializedAttributes;

@property (nonatomic) int tabValue;
@property (nonatomic) BOOL isRoot;

- (NSString *)tabber;
- (NSString *) textToHtml:(NSString *)text;
@end
