//
//  FHIR_TestTests.m
//  FHIR TestTests
//
//  Created by Andrew on 2014-01-27.
//  Copyright (c) 2014 Ideaworks. All rights reserved.
//

#import <XCTest/XCTest.h>
#import "FHIRParser.h"
#import "FHIRXmlWriter.h"
#import "FHIRJsonWriter.h"
#import "FHIRPatient.h"
#import "FHIRHumanName.h"
#import "FHIRIdentifier.h"
#import "FHIRCodeableConcept.h"
#import "FHIRCoding.h"
#import "FHIRErrorList.h"
#import "FHIRPost.h"

@interface FHIR_TestTests : XCTestCase

@property (nonatomic, strong) FHIRPost *post;
@property (nonatomic) BOOL loud;

@end

@implementation FHIR_TestTests

- (void)setUp
{
    [super setUp];
    // Put setup code here. This method is called before the invocation of each test method in the class.
    [self setPost:[FHIRPost new]];
    [self setLoud:YES];
}

- (void)tearDown
{
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

//- (void)testPatientValidation{
//    
//    NSLog(@"Patient 106 Validate... start");
//    
//    NSString *result = @"<?xml version=\"1.0\" encoding=\"UTF-8\"?><Patient xmlns=\"http://hl7.org/fhir\"><text><status value=\"generated\"/><div xmlns=\"http://www.w3.org/1999/xhtml\">West, James. MRN:&#x0A;            577425</div></text><identifier><label value=\"SSN\"/><system value=\"https://github.com/projectcypress/cypress/patient\"/><value value=\"577425\"/></identifier><name><use value=\"official\"/><family value=\"West\"/><given value=\"James\"/></name><gender><coding><system value=\"http://hl7.org/fhir/v3/AdministrativeGender\"/><code value=\"M\"/><display value=\"Male\"/></coding></gender><birthDate value=\"1946-06-08\"/><managingOrganization><reference value=\"Organization/1\"/></managingOrganization><active value=\"true\"/></Patient>";
//    
//    
//    FHIRPatient *patient = [FHIRParser mapInfoFromXMLString:result forOcType:@"Patient"];
//    FHIRErrorList *errors = [patient validate];
//    
//    XCTAssert([errors isValid], @"There are %d errors reported.", [[errors validation] count]);
//    
//    NSLog(@"Patient 106 Test - XML... finish");
//    
//}

- (void)testPatientCallXML{
    
    NSLog(@"Patient 106 Test - XML... start");
    
    NSURL *url = [NSURL URLWithString:@"http://hl7connect.healthintersections.com.au/open/patient/106?_format=xml"];
    NSString *result = [self requestUrl:url];
    
    //    NSString *result = @"<?xml version=\"1.0\" encoding=\"UTF-8\"?><Patient xmlns=\"http://hl7.org/fhir\"><text><status value=\"generated\"/><div xmlns=\"http://www.w3.org/1999/xhtml\">West, James. MRN:&#x0A;            577425</div></text><identifier><label value=\"SSN\"/><system value=\"https://github.com/projectcypress/cypress/patient\"/><value value=\"577425\"/></identifier><name><use value=\"official\"/><family value=\"West\"/><given value=\"James\"/></name><gender><coding><system value=\"http://hl7.org/fhir/v3/AdministrativeGender\"/><code value=\"M\"/><display value=\"Male\"/></coding></gender><birthDate value=\"1946-06-08\"/><managingOrganization><reference value=\"Organization/1\"/></managingOrganization><active value=\"true\"/></Patient>";
    //    NSLog(@"%@", result);
    
    FHIRPatient *patient = [FHIRParser mapInfoFromXMLString:result forOcType:@"Patient"];
    
    XCTAssert([[(FHIRIdentifier *)[patient.identifier objectAtIndex:0] value] isEqualToString:@"577425"], @"Id should match.");
    
    XCTAssert([[[(FHIRHumanName *)[patient.name objectAtIndex:0] family] objectAtIndex:0] isEqualToString:@"West"], @"Family Names should be equal.");
    
    XCTAssert([[(FHIRCoding *)[patient.gender.coding objectAtIndex:0] code] isEqualToString:@"M"], @"Gender codes should be equal.");
    
    
    if (self.loud){
        
        //        FHIRJsonWriter *jwriter = [[FHIRJsonWriter alloc] initWithFhirObject:patient];
        //        NSLog(@"%@", [jwriter serializedString]);
        
        FHIRXmlWriter *xwriter = [[FHIRXmlWriter alloc] initWithFhirObject:patient];
        NSLog(@"%@", [xwriter serializedString]);
    }
    
    
    NSLog(@"Patient 106 Test - XML... finish");
}

//- (void)testPatientCallJSON{
//
//    NSLog(@"Patient 106 Test - JSON... start");
//
//    NSURL *url = [NSURL URLWithString:@"http://hl7connect.healthintersections.com.au/open/patient/106?_format=json"];
//    NSString *result = [self requestUrl:url];
//
//    //    NSString *result = @"<?xml version=\"1.0\" encoding=\"UTF-8\"?><Patient xmlns=\"http://hl7.org/fhir\"><text><status value=\"generated\"/><div xmlns=\"http://www.w3.org/1999/xhtml\">West, James. MRN:&#x0A;            577425</div></text><identifier><label value=\"SSN\"/><system value=\"https://github.com/projectcypress/cypress/patient\"/><value value=\"577425\"/></identifier><name><use value=\"official\"/><family value=\"West\"/><given value=\"James\"/></name><gender><coding><system value=\"http://hl7.org/fhir/v3/AdministrativeGender\"/><code value=\"M\"/><display value=\"Male\"/></coding></gender><birthDate value=\"1946-06-08\"/><managingOrganization><reference value=\"Organization/1\"/></managingOrganization><active value=\"true\"/></Patient>";
//    //    NSLog(@"%@", result);
//
//    FHIRPatient *patient = [FHIRParser mapInfoFromJSONString:result forOcType:@"Patient"];
//
//    XCTAssert([[(FHIRIdentifier *)[patient.identifier objectAtIndex:0] value] isEqualToString:@"577425"], @"Id should match.");
//
//    XCTAssert([[[(FHIRHumanName *)[patient.name objectAtIndex:0] family] objectAtIndex:0] isEqualToString:@"West"], @"Family Names should be equal.");
//
//    XCTAssert([[(FHIRCoding *)[patient.gender.coding objectAtIndex:0] code] isEqualToString:@"M"], @"Gender codes should be equal.");
//
//
//    if (self.loud){
//
//        FHIRJsonWriter *jwriter = [[FHIRJsonWriter alloc] initWithFhirObject:patient];
//        NSLog(@"%@", [jwriter serializedString]);
//
//        FHIRXmlWriter *xwriter = [[FHIRXmlWriter alloc] initWithFhirObject:patient];
//        NSLog(@"%@", [xwriter serializedString]);
//    }
//
//
//    NSLog(@"Patient 106 Test - JSON... finish");
//}

//- (void)testPatientListXML{
//
//    NSLog(@"Patient List Test - XML... start");
//
//    NSArray *list = @[@"example", @"1",@"10",@"100",@"101",@"102",@"103",@"104",@"105",@"animal",@"dicom",@"f001",@"f201",@"glossy",@"ihe-pcd",@"pat1",@"pat2",@"xcda",@"xds"];
//    list = [NSArray new];
//
//    for (NSString *patientId in list) {
//
//        NSURL *url = [NSURL URLWithString:[NSString stringWithFormat:@"http://hl7connect.healthintersections.com.au/open/patient/%@?_format=xml", patientId]];
//        NSString *result = [self requestUrl:url];
//
//        FHIRPatient *patient = [FHIRParser mapInfoFromXMLString:result forOcType:@"Patient"];
//        NSString *serialized = [[[FHIRXmlWriter alloc] initWithFhirObject:patient] serializedString];
//        if ([patientId isEqualToString:@"example"]){
//            NSLog(@"STOP");
//        }
//        FHIRPatient *patient2 = [FHIRParser mapInfoFromXMLString:serialized forOcType:@"Patient"];
//
//
//
//        if (self.loud) {
//            NSLog(@"%@", result);
//            NSLog(@"%@", serialized);
//        }
//        FHIRHumanName *name = (FHIRHumanName *)[patient2.name objectAtIndex:0] ;
//        NSLog(@"Patient: %@, %@ %@", patientId, [[name given] objectAtIndex:0], [[name family] objectAtIndex:0]);
//
//        XCTAssert(patient2.identifier != nil, @"Something is not quite right.");
//    }
//
//    NSLog(@"Patient List Test - XML... finish");
//}


- (NSString *) requestUrl:(NSURL *)url{
    
    __block BOOL complete = NO;
    __block NSString *result = nil;
    
    [self.post performRequestWithUrl:url withData:nil withCompletion:^(NSData *data) {
        
        result = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
        complete = YES;
        
    } withFailure:^(NSError *error) {
        
        NSLog(@"ERROR: %@",[error.userInfo valueForKey:NSLocalizedDescriptionKey]);
        complete = YES;
        
    }];
    
    while (complete == NO) {
        
        sleep(3);
    }
    
    return result;
}

@end
