/*
  Copyright (c) 2011-2013, HL7, Inc.
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
  

 * Generated on Thu, Jan 30, 2014 05:26+1100 for FHIR v0.12
 */
/*
 * A conformance statement
 *
 * [FhirResource("Conformance")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRString;
@class FHIRContact;
@class FHIRCode;
@class FHIRBoolean;
@class FHIRDateTime;
@class FHIRConformanceSoftwareComponent;
@class FHIRConformanceImplementationComponent;
@class FHIRId;
@class FHIRResourceReference;
@class FHIRConformanceRestComponent;
@class FHIRConformanceMessagingComponent;
@class FHIRConformanceDocumentComponent;

@interface FHIRConformance : FHIRResource

/*
 * Whether the application produces or consumes documents
 */
typedef enum 
{
    kDocumentModeProducer, // The application produces documents of the specified type.
    kDocumentModeConsumer, // The application consumes documents of the specified type.
} kDocumentMode;

/*
 * The mode of a RESTful conformance statement
 */
typedef enum 
{
    kRestfulConformanceModeClient, // The application acts as a server for this resource.
    kRestfulConformanceModeServer, // The application acts as a client for this resource.
} kRestfulConformanceMode;

/*
 * The protocol used for message transport
 */
typedef enum 
{
    kMessageTransportHttp, // The application sends or receives messages using HTTP POST (may be over http or https).
    kMessageTransportFtp, // The application sends or receives messages using File Transfer Protocol.
    kMessageTransportMllp, // The application sends or receivers messages using HL7's Minimal Lower Level Protocol.
} kMessageTransport;

/*
 * The mode of a message conformance statement
 */
typedef enum 
{
    kConformanceEventModeSender, // The application sends requests and receives responses.
    kConformanceEventModeReceiver, // The application receives requests and sends responses.
} kConformanceEventMode;

/*
 * The impact of the content of a message
 */
typedef enum 
{
    kMessageSignificanceCategoryConsequence, // The message represents/requests a change that should not be processed more than once. E.g. Making a booking for an appointment.
    kMessageSignificanceCategoryCurrency, // The message represents a response to query for current information. Retrospective processing is wrong and/or wasteful.
    kMessageSignificanceCategoryNotification, // The content is not necessarily intended to be current, and it can be reprocessed, though there may be version issues created by processing old notifications.
} kMessageSignificanceCategory;

/*
 * Operations supported by REST at the type or instance level
 */
typedef enum 
{
    kRestfulOperationTypeRead,
    kRestfulOperationTypeVread,
    kRestfulOperationTypeUpdate,
    kRestfulOperationTypeDelete,
    kRestfulOperationTypeHistoryInstance,
    kRestfulOperationTypeValidate,
    kRestfulOperationTypeHistoryType,
    kRestfulOperationTypeCreate,
    kRestfulOperationTypeSearchType,
} kRestfulOperationType;

/*
 * The status of this conformance statement
 */
typedef enum 
{
    kConformanceStatementStatusDraft, // This conformance statement is still under development.
    kConformanceStatementStatusActive, // This conformance statement is ready for use in production systems.
    kConformanceStatementStatusRetired, // This conformance statement has been withdrawn or superceded and should no longer be used.
} kConformanceStatementStatus;

/*
 * Operations supported by REST at the system level
 */
typedef enum 
{
    kRestfulOperationSystemTransaction,
    kRestfulOperationSystemSearchSystem,
    kRestfulOperationSystemHistorySystem,
} kRestfulOperationSystem;

/*
 * Data types allowed to be used for search parameters
 */
typedef enum 
{
    kSearchParamTypeNumber, // Search parameter SHALL be a number (a whole number, or a decimal).
    kSearchParamTypeDate, // Search parameter is on a date/time. The date format is the standard XML format, though other formats may be supported.
    kSearchParamTypeString, // Search parameter is a simple string, like a name part. Search is case-insensitive and accent-insensitive. May match just the start of a string. String parameters may contain spaces.
    kSearchParamTypeToken, // Search parameter on a coded element or identifier. May be used to search through the text, displayname, code and code/codesystem (for codes) and label, system and key (for identifier). Its value is either a string or a pair of namespace and value, separated by a "|", depending on the modifier used.
    kSearchParamTypeReference, // A reference to another resource.
    kSearchParamTypeComposite, // A composite search parameter that combines a search on two values together.
    kSearchParamTypeVariable, // A search parameter on an element that can have multiple types including quantity and codeableConcept.
} kSearchParamType;

/*
 * Types of security services used with FHIR
 */
typedef enum 
{
    kRestfulSecurityServiceOAuth, // OAuth (see oauth.net).
    kRestfulSecurityServiceOAuth2, // OAuth version 2 (see oauth.net).
    kRestfulSecurityServiceNTLM, // Microsoft NTLM Authentication.
    kRestfulSecurityServiceBasic, // Basic authentication defined in HTTP specification.
    kRestfulSecurityServiceKerberos, // see http://www.ietf.org/rfc/rfc4120.txt.
} kRestfulSecurityService;

/*
 * Logical id to reference this statement
 */
@property (nonatomic, strong) FHIRString *identifierElement;

@property (nonatomic, strong) NSString *identifier;

/*
 * Logical id for this version of the statement
 */
@property (nonatomic, strong) FHIRString *versionElement;

@property (nonatomic, strong) NSString *version;

/*
 * Informal name for this conformance statement
 */
@property (nonatomic, strong) FHIRString *nameElement;

@property (nonatomic, strong) NSString *name;

/*
 * Publishing Organization
 */
@property (nonatomic, strong) FHIRString *publisherElement;

@property (nonatomic, strong) NSString *publisher;

/*
 * Contacts for Organization
 */
@property (nonatomic, strong) NSArray/*<Contact>*/ *telecom;

/*
 * Human description of the conformance statement
 */
@property (nonatomic, strong) FHIRString *descriptionElement;

@property (nonatomic, strong) NSString *description;

/*
 * draft | active | retired
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kConformanceStatementStatus status;

/*
 * If for testing purposes, not real usage
 */
@property (nonatomic, strong) FHIRBoolean *experimentalElement;

@property (nonatomic, strong) NSNumber *experimental;

/*
 * Publication Date
 */
@property (nonatomic, strong) FHIRDateTime *dateElement;

@property (nonatomic, strong) NSString *date;

/*
 * Software that is covered by this conformance statement
 */
@property (nonatomic, strong) FHIRConformanceSoftwareComponent *software;

/*
 * If this describes a specific instance
 */
@property (nonatomic, strong) FHIRConformanceImplementationComponent *implementation;

/*
 * FHIR Version
 */
@property (nonatomic, strong) FHIRId *fhirVersionElement;

@property (nonatomic, strong) NSString *fhirVersion;

/*
 * True if application accepts unknown elements
 */
@property (nonatomic, strong) FHIRBoolean *acceptUnknownElement;

@property (nonatomic, strong) NSNumber *acceptUnknown;

/*
 * formats supported (xml | json | mime type)
 */
@property (nonatomic, strong) NSArray/*<code>*/ *formatElement;

@property (nonatomic, strong) NSArray /*<NSString>*/ *format;

/*
 * Profiles supported by the system
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *profile;

/*
 * If the endpoint is a RESTful one
 */
@property (nonatomic, strong) NSArray/*<ConformanceRestComponent>*/ *rest;

/*
 * If messaging is supported
 */
@property (nonatomic, strong) NSArray/*<ConformanceMessagingComponent>*/ *messaging;

/*
 * Document definition
 */
@property (nonatomic, strong) NSArray/*<ConformanceDocumentComponent>*/ *document;

- (FHIRErrorList *)validate;

@end
