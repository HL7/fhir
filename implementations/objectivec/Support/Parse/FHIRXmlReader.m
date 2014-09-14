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
//  FHIRXmlReader.m
//  FHIR
//
//  Created by Adam Sippel on 2013-03-04.
//  Copyright (c) 2013 Mohawk College. All rights reserved.
//

#import "FHIRXmlReader.h"
#import "FHIRSerializerOrder.h"

//#define ARRAY_STRINGS [NSSet setWithObjects:@"given",@"family",@"prefix",@"suffix",@"link",@"identifier",@"name",@"telecom",@"address",@"language",@"part",@"line",@"coding",@"extensions",@"list",nil] //strings that need to be in an array even if only one object is present

NSString *const kXMLReaderTextNodeKey = @"value";

@interface FHIRXmlReader (Internal)

- (id)initWithError:(NSError *)error;
- (NSDictionary *)objectWithData:(NSData *)data;
- (NSArray *)enforceArray:(id)payload;

@end


@implementation FHIRXmlReader
{
    NSMutableArray *containedStringArrayKeys;
}
#pragma mark -
#pragma mark Public methods

+ (NSDictionary *)dictionaryForXMLData:(NSData *)data error:(NSError *)error
{
    FHIRXmlReader *reader = [[FHIRXmlReader alloc] initWithError:error];
    NSDictionary *rootDictionary = [reader objectWithData:data];
    return rootDictionary;
}

+ (NSDictionary *)dictionaryForXMLString:(NSString *)string error:(NSError *)error
{
    NSData *data = [string dataUsingEncoding:NSUTF8StringEncoding];
    return [FHIRXmlReader dictionaryForXMLData:data error:error];
}

- (NSMutableArray *)enforceArray:(id)payload
{
    if ([payload isKindOfClass:[NSMutableArray class]])
    {
        return payload;
    }else
    {
        if ([payload isKindOfClass:[NSNull class]] || (!payload)) return [NSMutableArray array];
        return [NSMutableArray arrayWithObject:payload];
    }
}

#pragma mark -
#pragma mark Parsing

- (id)initWithError:(NSError *)error
{
    if (self = [super init])
    {
        errorPointer = error;
    }
    return self;
}

- (NSDictionary *)objectWithData:(NSData *)data
{
    containedStringArrayKeys = [[NSMutableArray alloc] init];
    dictionaryStack = [[NSMutableArray alloc] init];
    textInProgress = [[NSMutableString alloc] init];
    
    // Initialize the stack with a fresh dictionary
    [dictionaryStack addObject:[NSMutableDictionary dictionary]];
    
    // Parse the XML
    NSXMLParser *parser = [[NSXMLParser alloc] initWithData:data];
    parser.delegate = self;
    BOOL success = [parser parse];
    
    // Return the stack's root dictionary on success
    if (success)
    {
        NSDictionary *resultDict = [dictionaryStack objectAtIndex:0];
        return resultDict;
    }
    
    return nil;
}

#pragma mark -
#pragma mark NSXMLParserDelegate methods

- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName attributes:(NSDictionary *)attributeDict
{
    // Get the dictionary for the current level in the stack
    NSMutableDictionary *parentDict = [dictionaryStack lastObject];
    
    // Create the child dictionary for the new element, and initilaize it with the attributes
    NSMutableDictionary *childDict = [NSMutableDictionary dictionary];
    [childDict addEntriesFromDictionary:attributeDict];
    
    // If there's already an item for this key, it means we need to create an array
    id existingValue = [parentDict objectForKey:elementName];
    if (existingValue)
    {
        NSMutableArray *array = nil;
        if ([existingValue isKindOfClass:[NSMutableArray class]])
        {
            // The array exists, so use it
            array = (NSMutableArray *) existingValue;
        }
        else
        {
            // Create an array if it doesn't exist
            array = [NSMutableArray array];
            [array addObject:existingValue];
            
            // Replace the child dictionary with an array of children dictionaries
            [parentDict setObject:array forKey:elementName];
        }
        
        // Add the new child dictionary to the array
        [array addObject:childDict];
    }
    else
    {
        //check if needs to be an array even with one item
        if (false)//[ARRAY_STRINGS containsObject:elementName])
        {
            [parentDict setObject:[self enforceArray:childDict] forKey:elementName];
            if ([elementName isEqualToString:@"contained"])
            {
                [containedStringArrayKeys addObject:elementName];
            }
        }
        else
        {
            // No existing value, so update the dictionary
            [parentDict setObject:childDict forKey:elementName];
        }
    }
    // Update the stack
    [dictionaryStack addObject:childDict];
}

- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName
{
    // Update the parent dict with text info
    NSMutableDictionary *dictInProgress = [dictionaryStack lastObject];
    
    // Set the text property
    //check if text exists, if there is more than just spaces, and if it is within a div container.
    //XMLReader does not handle div containers properly, so this fixes that problem
    if ([textInProgress length] > 0 && ([dictInProgress objectForKey:@"div"]))
    {
        //remove all spaces
        NSArray *words = [textInProgress componentsSeparatedByCharactersInSet :[NSCharacterSet whitespaceCharacterSet]];
        NSString* nospacestring = [words componentsJoinedByString:@""];
        
        //remove all new line markers
        NSArray *content = [nospacestring componentsSeparatedByString:@"\n"];
        NSString *finalString = [content componentsJoinedByString:@""];
        
        //add to dictionary final edited string
        [dictInProgress setObject:finalString forKey:@"div"];//kXMLReaderTextNodeKey];
        //NSLog(@"%@", textInProgress);
        
        // Reset the text
        textInProgress = [[NSMutableString alloc] init];
    }
    if (containedStringArrayKeys)
    {
        for (NSString *key in containedStringArrayKeys)
        {
            if ([textInProgress length] > 0 && ([dictInProgress objectForKey:key]))
            {
                //remove white space
                NSString *grabContent = [textInProgress stringByTrimmingCharactersInSet:
                                         [NSCharacterSet whitespaceAndNewlineCharacterSet]];
                [dictInProgress setObject:grabContent forKey:@"content"];
                
                // Reset the text
                textInProgress = [[NSMutableString alloc] init];
                //NSLog(@"GRABBY! %@ : ForDIct: %@ : DICTSTACK: %@",grabContent, dictInProgress, dictionaryStack);
            }
        }
    }
    
    // Pop the current dict
    [dictionaryStack removeLastObject];
}

- (void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string
{
    // Build the text value
    [textInProgress appendString:string];
}

- (void)parser:(NSXMLParser *)parser parseErrorOccurred:(NSError *)parseError
{
    // Set the error pointer to the parser's error object
    errorPointer = parseError;
}

@end
