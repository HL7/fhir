//
//  FRViewController.m
//  FhirTest
//
//  Created by Andrew on 2013-10-21.
//  Copyright (c) 2013 Ideaworks. All rights reserved.
//

#import "FRViewController.h"
#import "FHIRPatient.h"
#import "FHIRXmlWriter.h"
#import "FHIRJsonWriter.h"
#import "FHIRParser.h"

#import "FHIRBoolean.h"
#import "FHIRCodeableConcept.h"
#import "FHIRAnimalComponent.h"
#import "FHIRResourceReference.h"
#import "FHIRHumanName.h"
#import "FHIRCoding.h"
#import "FHIRNarrative.h"

@interface FRViewController ()

@property (nonatomic, strong) FHIRPatient *patient;

@end

@implementation FRViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    
    [self createPatient];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void) createPatient {
    
    FHIRPatient *patient = [FHIRPatient new];
    
    [patient setBirthDate:@"November 1, 2013"];
    
    FHIRHumanName *name = [FHIRHumanName new];
    [name setUse:kNameUseOfficial];
    [name setText:@"John Smith"];
    [name setFamily:@[@"Smith", @"Smith2"]];
    [name setGiven:@[@"John", @"Jane"]];
    
    [patient setName:@[name]];
    
    // no telecom
    
    {
        FHIRCodeableConcept *concept = [FHIRCodeableConcept new];
        [concept setText:@"male"];
        [patient setGender:concept];
    }
    
    [patient setDeceased:[[FHIRBoolean alloc] initWithValue:[NSNumber numberWithBool:YES]]];
    
    // no address
    
    {
        FHIRCodeableConcept *concept = [FHIRCodeableConcept new];
        [concept setText:@"Single"];
        [patient setMaritalStatus:concept];
    }
    
    [patient setMultipleBirth:[[FHIRBoolean alloc] initWithValue:[NSNumber numberWithBool:YES]]];
    
    // no photos set
    // no contact set
    
    FHIRAnimalComponent *animal = [FHIRAnimalComponent new];
    
    {
        FHIRCodeableConcept *concept = [FHIRCodeableConcept new];
        {
            FHIRCoding *coding = [FHIRCoding new];
            [coding setSystem:@"http://hl7.org/fhir/animal-species"];
            [coding setCode:@"canislf"];
            [coding setDisplay:@"Dog"];
            [concept setCoding:@[coding]];
        }
        [animal setSpecies:concept];
    }
    {
        FHIRCodeableConcept *concept = [FHIRCodeableConcept new];
        FHIRCoding *coding = [FHIRCoding new];
        [coding setSystem:@"http://snomed.info/id"];
        [coding setCode:@"58108001"];
        [coding setDisplay:@"Golden retriever"];
    
        FHIRCoding *coding2 = [FHIRCoding new];
        [coding2 setSystem:@"http://hl7.org/fhir/animal-breed"];
        [coding2 setCode:@"gret"];
        [coding2 setDisplay:@"Golden Retriever"];
        [concept setCoding:@[coding,coding2]];

        [animal setBreed:concept];
    }
    {
        FHIRCodeableConcept *concept = [FHIRCodeableConcept new];
        {
            FHIRCoding *coding = [FHIRCoding new];
            [coding setSystem:@"http://hl7.org/fhir/animal-genderstatus"];
            [coding setCode:@"Neutered"];
            [concept setCoding:@[coding]];
        }
        [animal setGenderStatus:concept];
    }
    
    [patient setAnimal:animal];
    
    {
        FHIRCodeableConcept *concept1 = [FHIRCodeableConcept new];
        [concept1 setText:@"english"];
        FHIRCodeableConcept *concept2 = [FHIRCodeableConcept new];
        [concept2 setText:@"english"];
        [patient setCommunication:@[concept1, concept2]];
    }
    
    FHIRResourceReference *reference = [FHIRResourceReference new];
    [reference setReference:@"url provider reference?"];
    [reference setDisplay:@"Pete's Vetinary Services"];
    [patient setManagingOrganization:reference];
    
    // no link set
    
    [patient setActive:[NSNumber numberWithInt:50]];
    
    FHIRNarrative *narrative = [[FHIRNarrative alloc] init];
    [narrative setDiv:@"<p>Patient Donald DUCK @ Acme Healthcare, Inc. MR = 654321</p>"];
    [patient setText:narrative];
    
    [self setPatient:patient];
}

- (void) serializePatientXML{
    
    NSLog(@"%@", [[[FHIRXmlWriter alloc] initWithFhirObject:self.patient] serializedString]);
    
}

- (void) serializePatientJSON {
    
    NSLog(@"%@", [[[FHIRJsonWriter alloc] initWithFhirObject:self.patient] serializedString]);
}

- (void) parsePatientXML {
    
    //NSLog(@"%@", [FHIRParser mapInfoFromXMLString:[[[FHIRSerializer alloc] initWithFhirObject:self.patient] xml]]);
    id object = [FHIRParser mapInfoFromXMLString:[[[FHIRXmlWriter alloc] initWithFhirObject:self.patient] serializedString] forOcType:@"Patient"];
    if ([self.patient class] == [object class])
    {
        NSLog(@"PASS");
        NSLog(@"%@", [[[FHIRXmlWriter alloc] initWithFhirObject:object] serializedString]);
        NSLog(@"%@", [[[FHIRXmlWriter alloc] initWithFhirObject:self.patient] serializedString]);
        if([[NSString stringWithFormat:@"%@", [[[FHIRXmlWriter alloc] initWithFhirObject:object] serializedString]] isEqualToString:[NSString stringWithFormat:@"%@", [[[FHIRXmlWriter alloc] initWithFhirObject:self.patient] serializedString]]])
        {
            NSLog(@"PERFECT PASS");
        }
        
    }
    else
    {
        NSLog(@"%@ is not %@", self.patient, object);
    }
    
}

- (void) parsePatientJSON {
    NSLog(@"%@", [FHIRParser mapInfoFromJSONString:[[[FHIRJsonWriter alloc] initWithFhirObject:self.patient] serializedString] forOcType:@"Patient"]);
}

- (IBAction)createXML:(id)sender {
    [self serializePatientXML];
}

- (IBAction)createJSON:(id)sender {
    [self serializePatientJSON];
}

- (IBAction)parseXML:(id)sender {
    [self parsePatientXML];
}

- (IBAction)parseJSON:(id)sender {
    [self parsePatientJSON];
}
@end
