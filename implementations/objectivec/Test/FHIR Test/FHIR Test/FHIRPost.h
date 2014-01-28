//
//  FHIRPost.h
//  FhirTest
//
//  Created by Andrew on 2014-01-08.
//  Copyright (c) 2014 Ideaworks. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef void (^FHIRConnectionDelegateSuccess) (NSData *data);
typedef void (^FHIRConnectionDelegateFailure) (NSError *error);

@interface FHIRPost : NSObject <NSURLConnectionDelegate, NSURLConnectionDataDelegate> {
    NSMutableData *response;
}

@property (nonatomic) NSTimeInterval timeout;
@property (nonatomic) NSURLRequestCachePolicy cache;

- (void) performRequestWithUrl:(NSURL *)url withData:(NSData *)data withCompletion:(FHIRConnectionDelegateSuccess)completion withFailure:(FHIRConnectionDelegateFailure)failure;
- (void) cancelAll;

@end
