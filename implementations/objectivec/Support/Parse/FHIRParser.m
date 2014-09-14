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

#import <objc/runtime.h>

#import "FHIRParser.h"
#import "FHIRXmlReader.h"
#import "FHIRSerializerOrderPair.h"

@implementation FHIRParser

static NSArray *fhirPrimitives;

+ (void)initialize // this method is called *once* for every class, before it is used for the first time (not necessarily when the app is first launched)
{
    [super initialize];
    
    fhirPrimitives = [[NSArray alloc] initWithObjects:@"FHIRDate",@"FHIRString",@"FHIRDateTime",@"FHIRDecimal",@"FHIRInstant",@"FHIRInteger",@"FHIRUri",@"FHIRBoolean",@"FHIRBase64Binary",@"FHIRCode", nil];
}

+ (NSArray *)fhirPrimitivesArray
{
    return fhirPrimitives;
}

+ (id)mapInfoFromJSONString:(NSString *)jsonString forOcType:(NSString *)ocTypeString
{
    NSStringEncoding  encoding = NSASCIIStringEncoding;
    NSData * jsonData = [jsonString dataUsingEncoding:encoding];
    NSError * error=nil;
    NSDictionary * parsedData = [NSJSONSerialization JSONObjectWithData:jsonData options:kNilOptions error:&error];
    //return parsedData;
    
    return [self loadObjectName:[NSString stringWithFormat:@"FHIR%@",ocTypeString] withDictionary:[parsedData objectForKey:ocTypeString]];
}

+ (id)mapInfoFromXMLString:(NSString *)xmlString forOcType:(NSString *)ocTypeString
{
    NSDictionary *parsedData = [FHIRXmlReader dictionaryForXMLString:xmlString error:nil];
    
    return [self loadObjectName:[NSString stringWithFormat:@"FHIR%@",ocTypeString] withDictionary:[parsedData objectForKey:ocTypeString]];
    
}

+ (id)loadObjectName:(NSString *)ocType withDictionary:(NSDictionary *)content
{
    id someInstance = [[NSClassFromString(ocType) alloc] init];
    
    NSArray *keySets = [FHIRSerializerOrderPair orderPairArrayForType:ocType];
    NSMutableDictionary *classTypes = [NSMutableDictionary new];
    
    NSString *pattern = @"(?<=\").*(?=\")";
    NSError *error;
    NSRegularExpression* regex = [NSRegularExpression regularExpressionWithPattern:pattern options:0 error:&error];
    Class instancType = [someInstance class];
    
    do {
    
        unsigned int outCount, i;
        objc_property_t *properties = class_copyPropertyList([someInstance class], &outCount);
        for (i = 0; i < outCount; i++) {
            objc_property_t property = properties[i];
            
            NSString *search = [NSString stringWithCString:property_getAttributes(property) encoding:NSUTF8StringEncoding];
            
            NSTextCheckingResult *match = [regex firstMatchInString:search options:0 range: NSMakeRange(0, [search length])];
            
            [classTypes setObject:[search substringWithRange:[match rangeAtIndex:0]]
                      forKey:[NSString stringWithCString:property_getName(property) encoding:NSUTF8StringEncoding]];
        }
        
        instancType = [instancType superclass];
        
    } while (instancType != [NSObject class]);
        
    for (NSArray* keySet in keySets)
    {
        NSString *key = [keySet objectAtIndex:0];
        NSString *type = [keySet objectAtIndex:1];
        Class classType;
        
        if ([[classTypes objectForKey:key] length] < 1) {
            classType = NSClassFromString(type);
        } else {
            classType = NSClassFromString([classTypes objectForKey:key]);
        }
        
        id value = [content valueForKey:key];
        
        SEL selector = NSSelectorFromString(key);
        if ([someInstance respondsToSelector:selector] && value) {
            
            if (classType == [NSArray class]) {
                
                if ([value isKindOfClass:[NSArray class]]){
                
                    NSMutableArray *array = [NSMutableArray new];
                    
                    for (id childValue in value){
                        
                        id child = [self loadObjectName:type withDictionary:childValue];
                        [array addObject:child];
                        
                    }
                    value = [NSArray arrayWithArray:array];
                    
                } else {
                    // value is a single object
                    value = [NSArray arrayWithObject:[self loadObjectName:type withDictionary:value]];
                }
            } else if ([value isKindOfClass:[NSDictionary class]]){
            
                // object type
                
                value = [self loadObjectName:type withDictionary:value];
            }
            
            NSString *elementKey = [NSString stringWithFormat:@"%@Element", key];
            SEL valueSelector = NSSelectorFromString(elementKey);
            if ([someInstance respondsToSelector:valueSelector]) {
                key = elementKey;
            }
            
            [someInstance setValue:value forKey:key];
        }
        
    }
    return someInstance;
}

@end
