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
//  FHIRXmlWriter.m
//  FhirTest
//
//  Created by Andrew on 2013-10-25.
//  Copyright (c) 2013 Ideaworks. All rights reserved.
//

#import "FHIRXmlWriter.h"
#import "FHIRModelInfo.h"
#import "FHIRSerializerOrder.h"
#import "FHIRSerializer.h"

@implementation FHIRXmlWriter

/**
 * Write header of file.
 */
- (void)writeHeader {
    [self.serializedString appendString:@"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"];
}

/**
 * Write footer of file.
 */
- (void)writeFooter {
    
}

/**
 * Write directly to xml.
 */
- (void) writeContent:(NSString *)content {
    [self.serializedString appendString:content];
}

/**
 * Write an Element with Attributes and Content.
 */
- (void)writeElement:(NSString *)element withAttributes:(NSDictionary *)attributes withContent:(NSString *)content {
    
    [self writeElementOpen:element withAttributes:attributes];
    if (content && [content length] > 0){
        [self.serializedString appendString:content];
        [self writeElementClose:element isSelfClosing:NO];
    } else {
        [self writeElementClose:element isSelfClosing:YES];
    }
}

/**
 * Write an array of Elements with Attributes and Content.
 */
- (void)writeElement:(NSString *)element withAttributesArray:(NSArray *)attributesArray withContentArray:(NSArray *)contentArray {
    
    for (int i = 0; i < [contentArray count]; i++) {
        
        [self writeElement:element
            withAttributes:[attributesArray objectAtIndex:i]
               withContent:[contentArray objectAtIndex:i]];
    }
}

/**
 * Write opening Element tag.
 */
- (void)writeElementOpen:(NSString *)element withAttributes:(NSDictionary *)attributes {
    
    if (self.isRoot) {
        
        [self.serializedString appendFormat:@"<%@ xmlns=\"http://hl7.org/fhir\">", element];
        
    } else if ([element isEqualToString:@"div"]){
        
        [self.serializedString appendString:[self tabber]];
        [self.serializedString appendString:@"<div xmlns=\"http://www.w3.org/1999/xhtml\">"];
        
    } else if (attributes != nil) {
        
        [self.serializedString appendString:[self tabber]];
        [self.serializedString appendFormat:@"<%@ ", element];
        
        for (NSString *key in [attributes allKeys]) {
            
            [self.serializedString appendFormat:@"%@=\"%@\" ", key, [attributes valueForKey:key]];
        }
        
        [self.serializedString appendString:@">"];
        
    } else {
        
        [self.serializedString appendString:[self tabber]];
        [self.serializedString appendFormat:@"<%@>", element];
    }
    
    self.tabValue++;
}

/**
 * Write closing element tag.
 */
- (void)writeElementClose:(NSString *)element isSelfClosing:(BOOL)isSelfClosing{
    
    self.tabValue--;
    
    if (isSelfClosing){
        [self.serializedString deleteCharactersInRange:NSMakeRange([self.serializedString length] - 1 , 1)];
        [self.serializedString appendString:@"/>"];
    } else if ([element isEqualToString:@"div"]) {
        [self.serializedString appendString:@"</div>"];
    } else {
        [self.serializedString appendString:[self tabber]];
        [self.serializedString appendFormat:@"</%@>", element];
    }
}

@end
