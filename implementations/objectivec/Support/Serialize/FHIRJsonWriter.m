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
//  FHIRJsonWriter.m
//  FhirTest
//
//  Created by Andrew on 2013-11-28.
//  Copyright (c) 2013 Ideaworks. All rights reserved.
//

#import "FHIRJsonWriter.h"

@interface FHIRJsonWriter ()

- (void) checkPrintComma;

@end

@implementation FHIRJsonWriter

- (void)writeHeader {
    [self.serializedString appendString:@"{"];
    self.tabValue++;
}

- (void)writeFooter {
    
    [self.serializedString appendString:[self tabber]];
    [self.serializedString appendString:@"}"];
    self.tabValue--;
    [self.serializedString appendString:@"\n}"];
}

- (void) writeContent:(NSString *)content {
    [self.serializedString appendFormat:@"\"%@\"", content];
}

- (void)writeElement:(NSString *)element withAttributes:(NSDictionary *)attributes withContent:(NSString *)content {
    
    [self checkPrintComma];
    
    [self writeElementOpen:element];
    
    BOOL hasAttributes = attributes || [element isEqualToString:@"div"];
    
    if ( !hasAttributes ){
        [self.serializedString appendString:@"{"];
    }
    
    if ([element isEqualToString:@"div"]){
        
        [self.serializedString appendString:@"\"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\">"];
        [self.serializedString appendString:[self textToHtml:content]];
        [self.serializedString appendString:@"</div>\""];
        
    } else {
        [self.serializedString appendString:content];
    }
    
    // close object
    self.tabValue--;
    if ( !hasAttributes ){
        [self.serializedString appendString:[self tabber]];
        [self.serializedString appendString:@"}"];
    }
}

- (void)writeElement:(NSString *)element withAttributesArray:(NSArray *)attributesArray withContentArray:(NSArray *)contentArray {
    
    [self checkPrintComma];
    
    [self writeElementOpen:element];
    [self.serializedString appendString:@"["];
    
    BOOL isPrimitive = attributesArray != nil;
    
    if ( !isPrimitive ){
        [self.serializedString appendString:@"{"];
    }
    
    for (int i=0; i< [contentArray count]; i++) {
        NSString *content = [contentArray objectAtIndex:i];
        
        [self.serializedString appendString:content];
        
        if ((i + 1) != [contentArray count]) {
            if ( isPrimitive ) {
                [self.serializedString appendString:@", "];
            } else {
                self.tabValue--;
                [self.serializedString appendString:[self tabber]];
                [self.serializedString appendString:@"}, {"];
                self.tabValue++;
            }
        }
    }
    
    // close object
    self.tabValue--;
    if ( !isPrimitive ){
        [self.serializedString appendString:[self tabber]];
        [self.serializedString appendString:@"}"];
    }
    [self.serializedString appendString:@"]"];
}

//- (void)writeValueElement:(NSString *)element withValue:(NSString *)value withContent:(NSString *)content {

    // todo different in json then xml for family->value/extension?

//}

- (void)writeElementOpen:(NSString *)element {
    
    [self.serializedString appendString:[self tabber]];
    [self.serializedString appendFormat:@"\"%@\" : ", element];
    
    if ( self.isRoot ){
        [self.serializedString appendString:@"{"];
    }
    
    
    self.tabValue++;
}

- (void)writeElementClose:(NSString *)element {
    
    self.tabValue--;
}

- (void)checkPrintComma {
    
    if ( [self.serializedString length] != 0
        && ![self.serializedString hasSuffix:@"{"]) {
        [self.serializedString appendString:@", "];
    }
}

@end
