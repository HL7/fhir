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
//  FHIRSerializer.m
//  FhirTest
//
//  Created by Andrew on 2013-10-23.
//  Copyright (c) 2013 Ideaworks. All rights reserved.
//

#import "FHIRSerializer.h"

#import "FHIRSerializerOrder.h"
#import "FHIRModelInfo.h"

@interface FHIRSerializer ()

- (void) load;
- (void) loadAttribute:(id)attribute withKey:(NSString *)key;
- (void) loadArray:(NSArray *)attribute withKey:(NSString *)key;

@end

@implementation FHIRSerializer

static NSArray *fhirPrimitives;

+ (void)initialize {
    [super initialize];
   
    //todo move/generate
    fhirPrimitives = [[NSArray alloc] initWithObjects:@"FHIRDate",@"FHIRString",@"FHIRDateTime",@"FHIRDecimal",@"FHIRInstant",@"FHIRInteger",@"FHIRUri",@"FHIRBoolean",@"FHIRBase64Binary",@"FHIRCode", nil];
}

/**
 * Initialize with fire object.
 */
- (id)initWithFhirObject:(id)fhirObject {
    
    self = [super init];
    if (self) {
        [self setFhirObject:fhirObject];
        [self setSerializedObject:[NSMutableDictionary new]];
        [self setFhirOcType:NSStringFromClass([self.fhirObject class])];
        [self setSerializedString:[NSMutableString new]];
        [self setIsRoot:YES];
        
        [self load];
    }
    return self;
}

/**
 * Initialize with fire object, and indent.
 */
- (id)initWithFhirObject:(id)fhirObject andTabValue:(int)tabValue {
    
    self = [super init];
    if (self) {
        [self setFhirObject:fhirObject];
        [self setSerializedObject:[NSMutableDictionary new]];
        [self setFhirOcType:NSStringFromClass([self.fhirObject class])];
        [self setSerializedString:[NSMutableString new]];
        [self setTabValue:tabValue];
        
        [self loadContent];
    }
    return self;
}

/**
 * Load for serialization.
 */
- (void) load {
    
    NSString *classType = NSStringFromClass([self.fhirObject class]);
    NSString *rootElement = [[[FHIRModelInfo instance] ocTypeToFhirString] objectForKey:classType];
    
    // write header
    [self writeHeader];
    [self writeElementOpen:rootElement withAttributes:nil];
    [self setIsRoot:false];
    [self loadContent];
    [self writeElementClose:rootElement isSelfClosing:NO];
    [self writeFooter];
}

/**
 * Load content
 */
- (void)loadContent {
    
    NSString *classType = NSStringFromClass([self.fhirObject class]);
    NSArray *keys = [FHIRSerializerOrder orderArrayForType:classType];
    
    // Generate in order
    for (NSString *key in keys) {

        // Primitive type
        NSString *keySuffixed = [NSString stringWithFormat:@"%@Element", key];
        
        // get attribute
        id attribute;
        // check if the object responds to the Element type.
        if ( [self.fhirObject respondsToSelector:NSSelectorFromString(keySuffixed)] ){
            
            attribute = [self.fhirObject valueForKey: keySuffixed];
        } else {
            
            attribute = [self.fhirObject valueForKey:key];
        }
        
        // if attribute exists continue
        if (attribute) {
            
            if ([attribute isKindOfClass:[NSArray class]]) {
                
                // load array attribute
                [self loadArray:attribute withKey:key];
                
            } else {
                
                // load attribute
                [self loadAttribute:attribute withKey:key];
            }
        }
    }
}

/**
 * Load array content
 */
- (void)loadArray:(NSArray *)attribute withKey:(NSString *)key {
    
    // if content exists
    if ([attribute count] > 0) {
        
        NSMutableArray *serializedArray = [NSMutableArray new];
        NSMutableArray *stringArray = [NSMutableArray new];
        NSMutableArray *attributesArray;
        
        BOOL elementType = [self.fhirObject respondsToSelector:NSSelectorFromString([NSString stringWithFormat:@"%@Element", key])];
        
        if (elementType) {
            attributesArray = [NSMutableArray new];
        }
        
        // generate each attribute
        for (id value in attribute) {
            
            NSString *type = NSStringFromClass([value class]);
            NSString *fhir = [[[FHIRModelInfo instance] ocTypeToFhirString] valueForKey:type];
            
            // if the attribute is a fhir object, generate it
            if (fhir) {
                
                FHIRSerializer *serializer = [[NSClassFromString(NSStringFromClass(self.class)) alloc] initWithFhirObject:value andTabValue:(self.tabValue + 1)];
                
                // if the attribute is an element type
                if (elementType) {
                    [serializedArray addObject:[[serializer serializedObject] valueForKey:@"value"]];
                } else {
                    // normal type
                    [serializedArray addObject:[serializer serializedObject]];
                }
                
                [attributesArray addObject:[serializer serializedAttributes]];
                [stringArray addObject:[serializer serializedString]];
            }
            else {

                // attribute is a primitive and needs to be printed as a string
                [serializedArray addObject:value];
                [stringArray addObject:[NSString stringWithFormat:@"%@", value]];
                
            }
        }
        
        // generate dictionary/string
        [self.serializedObject setObject:serializedArray forKey:key];
        [self writeElement:key withAttributesArray:attributesArray withContentArray:stringArray];
    }
}

/**
 * Load attribute content
 */
- (void)loadAttribute:(id)attribute withKey:(NSString *)key {
    
    NSString *type = NSStringFromClass([attribute class]);
    NSString *fhir = [[[FHIRModelInfo instance] ocTypeToFhirString] valueForKey:type];
    
    // if the attribute is a fhir object, generate it
    if (fhir) {
        
        FHIRSerializer *serializer = [[NSClassFromString(NSStringFromClass(self.class)) alloc] initWithFhirObject:attribute andTabValue: (self.tabValue + 1)];
        
        [self.serializedObject setObject:serializer.serializedObject forKey:key];
        
        
        [self writeElement:key withAttributes:serializer.serializedAttributes withContent:serializer.serializedString];
        
    } else if ( [key isEqualToString:@"div"]){
        
        // div element
        attribute = [self textToHtml:attribute];
        [self.serializedObject setObject:attribute forKey:key];
        [self writeElement:key withAttributes:nil withContent:attribute];
        
    } else if ( [key isEqualToString:@"value"] ) {
        
        if (!self.serializedAttributes) {
            [self setSerializedAttributes:[NSMutableDictionary new]];
        }
        
        [self.serializedObject setObject:attribute forKey:key];
        [self.serializedAttributes setObject:attribute forKey:key];
        
    } else {
        // other stuff
        
        [self.serializedObject setObject:attribute forKey:key];
        [self writeContent:[NSString stringWithFormat:@"%@", attribute]];
    }
}

/** 
 * formats the string for easier reading
 */
- (NSString *)tabber
{
    NSMutableString *tabs = [NSMutableString new];
    
    for (int i = 0; i < self.tabValue; i++)
    {
        [tabs appendString:@"   "];
    }
    
    return [NSString stringWithFormat:@"\n%@", tabs ];
}

/**
 * Convert to HTML.
 */
- (NSString*)textToHtml:(NSString*)text {
    
    text = [text stringByReplacingOccurrencesOfString:@"&"  withString:@"&amp;"];
    text = [text stringByReplacingOccurrencesOfString:@"<"  withString:@"&lt;"];
    text = [text stringByReplacingOccurrencesOfString:@">"  withString:@"&gt;"];
    text = [text stringByReplacingOccurrencesOfString:@"""" withString:@"&quot;"];
    text = [text stringByReplacingOccurrencesOfString:@"'"  withString:@"&#039;"];
    text = [text stringByReplacingOccurrencesOfString:@"\n" withString:@"&#x0A;"];
    return text;
}

// add to child classes
- (void)writeHeader {}
- (void)writeFooter {}
- (void)writeContent:(NSString *)content {}
- (void)writeElementOpen:(NSString *)element withAttributes:(NSDictionary *)attributes {}
- (void)writeElementClose:(NSString *)element isSelfClosing:(BOOL)isSelfClosing {}
- (void)writeElement:(NSString *)element withAttributes:(NSDictionary *)attributes withContent:(NSString *)content {}
- (void)writeElement:(NSString *)element withAttributesArray:(NSArray *)attributesArray withContentArray:(NSArray *)contentArray {}

@end
