//
//  FHIRPost.m
//  FhirTest
//
//  Created by Andrew on 2014-01-08.
//  Copyright (c) 2014 Ideaworks. All rights reserved.
//
// https://gist.github.com/spr/4751706#file-exampledelegate-h

#import "FHIRPost.h"

@interface FHIRUrlConnection : NSURLConnection

@property (nonatomic, strong) NSMutableData *data;
@property (nonatomic, strong) NSURLResponse *response;
@property (nonatomic, strong) FHIRConnectionDelegateSuccess completion;
@property (nonatomic, strong) FHIRConnectionDelegateFailure failure;

@end

@implementation FHIRUrlConnection

@end

@interface FHIRPost ()


@property (nonatomic, strong) NSMutableArray *connections;
@property (nonatomic, strong) NSOperationQueue *queue;

@end

@implementation FHIRPost

- (id)init
{
    self = [super init];
    if (self) {
        
        _queue = [NSOperationQueue new];
        _queue.maxConcurrentOperationCount = 1;
        _connections = [NSMutableArray arrayWithCapacity:10];
        _timeout = 5.0;
        _cache = NSURLRequestUseProtocolCachePolicy;
        
    }
    return self;
}

- (void) performRequestWithUrl:(NSURL *)url withData:(NSData *)data withCompletion:(FHIRConnectionDelegateSuccess)completion withFailure:(FHIRConnectionDelegateFailure)failure{
    
    NSMutableURLRequest *request = [NSMutableURLRequest new];
    [request setURL:url];
    if (data){
        [request setHTTPBody:data];
    }
    //[request setHTTPMethod:@"POST"];
    [request setCachePolicy:self.cache];
    [request setTimeoutInterval:self.timeout];
    FHIRUrlConnection *connection = [[FHIRUrlConnection alloc] initWithRequest:request delegate:self startImmediately:NO];
    
    if (connection) {
        
        [connection setDelegateQueue:self.queue];
        connection.data = [NSMutableData dataWithCapacity:1024];
        connection.completion = completion;
        connection.failure = failure;
        
        [connection start];
        [self.connections addObject:connection];
        
    } else {
        failure([NSError errorWithDomain:@"ExampleDelegate" code:-1 userInfo:@{NSLocalizedDescriptionKey: @"Could not initialize NSURLConnection"}]);
    }
}

- (void)cancelAll {
    [self.queue setSuspended:YES];
    [self.queue cancelAllOperations];
    [self.queue addOperationWithBlock:^{
        for (FHIRUrlConnection *connection in self.connections) {
            [connection cancel];
            connection.failure([NSError errorWithDomain:@"FHIRConnection" code:-2 userInfo:@{NSLocalizedDescriptionKey: @"User Cancelled Calls"}]);
        }
        [self.connections removeAllObjects];
    }];
    [self.queue setSuspended:NO];
}

#pragma mark NSURLConnectionDelegate Methods

- (void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error{
    
    FHIRUrlConnection *fhirConnection = (FHIRUrlConnection *)connection;
    fhirConnection.failure(error);
    [self.connections removeObject:connection];
}

#pragma mark NSURLConnectionDataDelegate Methods

- (void)connection:(NSURLConnection *)connection didReceiveResponse:(NSURLResponse *)response{
    
    FHIRUrlConnection *fhirConnection = (FHIRUrlConnection *)connection;
    [fhirConnection setResponse:response];
    [fhirConnection.data setLength:0];
}

- (void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data{
    
    [[(FHIRUrlConnection *)connection data] appendData:data];
}

- (void)connectionDidFinishLoading:(NSURLConnection *)connection{
    
    [self.connections removeObject:connection];
    
    FHIRUrlConnection *fhirConnection = (FHIRUrlConnection *)connection;
    
    if ([fhirConnection.response isKindOfClass:[NSHTTPURLResponse class]]){
        NSHTTPURLResponse *httpResponse = (NSHTTPURLResponse *)fhirConnection.response;
        if (httpResponse.statusCode >= 400) {
            // client/server error
            fhirConnection.failure([NSError errorWithDomain:@"FHIRUrlConnection" code:httpResponse.statusCode userInfo:@{NSLocalizedDescriptionKey: [NSHTTPURLResponse localizedStringForStatusCode:httpResponse.statusCode]}]);
            return;
        }
    }
    
    fhirConnection.completion(fhirConnection.data);
}

@end
