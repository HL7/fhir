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
  

 * Generated on Mon, Jan 27, 2014 13:55-0500 for FHIR v0.12
 */
/*
 * Resource Profile
 *
 * [FhirResource("Profile")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRString;
@class FHIRContact;
@class FHIRCoding;
@class FHIRCode;
@class FHIRBoolean;
@class FHIRDateTime;
@class FHIRId;
@class FHIRProfileMappingComponent;
@class FHIRProfileStructureComponent;
@class FHIRProfileExtensionDefnComponent;

@interface FHIRProfile : FHIRResource

/*
 * Binding conformance for applications
 */
typedef enum 
{
    kBindingConformanceRequired, // Only codes in the specified set are allowed.  If the binding is extensible, other codes may be used for concepts not covered by the bound set of codes.
    kBindingConformancePreferred, // For greater interoperability, implementers are strongly encouraged to use the bound set of codes, however alternate codes may be used in derived profiles and implementations if necessary without being considered non-conformant.
    kBindingConformanceExample, // The codes in the set are an example to illustrate the meaning of the field. There is no particular preference for its use nor any assertion that the provided values are sufficient to meet implementation needs.
} kBindingConformance;

/*
 * SHALL applications comply with this constraint?
 */
typedef enum 
{
    kConstraintSeverityError, // If the constraint is violated, the resource is not conformant.
    kConstraintSeverityWarning, // If the constraint is violated, the resource is conformant, but it is not necessarily following best practice.
} kConstraintSeverity;

/*
 * The lifecycle status of a Resource Profile
 */
typedef enum 
{
    kResourceProfileStatusDraft, // This profile is still under development.
    kResourceProfileStatusActive, // This profile is ready for normal use.
    kResourceProfileStatusRetired, // This profile has been deprecated, withdrawn or superseded and should no longer be used.
} kResourceProfileStatus;

/*
 * How a property is represented on the wire
 */
typedef enum 
{
    kPropertyRepresentationXmlAttr, // In XML, this property is represented as an attribute not an element.
} kPropertyRepresentation;

/*
 * How resource references can be aggregated
 */
typedef enum 
{
    kAggregationModeContained, // The reference is a local reference to a contained resource.
    kAggregationModeReferenced, // The reference to to a resource that has to be resolved externally to the resource that includes the reference.
    kAggregationModeBundled, // The resource the reference points to will be found in the same bundle as the resource that includes the reference.
} kAggregationMode;

/*
 * How an extension context is interpreted
 */
typedef enum 
{
    kExtensionContextResource, // The context is all elements matching a particular resource element path.
    kExtensionContextDatatype, // The context is all nodes matching a particular data type element path (root or repeating element) or all elements referencing a particular primitive data type (expressed as the datatype name).
    kExtensionContextMapping, // The context is all nodes whose mapping to a specified reference model corresponds to a particular mapping structure.  The context identifies the mapping target. The mapping should clearly identify where such an extension could be used, though this.
    kExtensionContextExtension, // The context is a particular extension from a particular profile.  Expressed as uri#name, where uri identifies the profile and #name identifies the extension code.
} kExtensionContext;

/*
 * How slices are interpreted when evaluating an instance
 */
typedef enum 
{
    kSlicingRulesClosed, // No additional content is allowed other than that described by the slices in this profile.
    kSlicingRulesOpen, // Additional content is allowed anywhere in the list.
    kSlicingRulesOpenAtEnd, // Additional content is allowed, but only at the end of the list.
} kSlicingRules;

/*
 * Logical id to reference this profile
 */
@property (nonatomic, strong) FHIRString *identifierElement;

@property (nonatomic, strong) NSString *identifier;

/*
 * Logical id for this version of the profile
 */
@property (nonatomic, strong) FHIRString *versionElement;

@property (nonatomic, strong) NSString *version;

/*
 * Informal name for this profile
 */
@property (nonatomic, strong) FHIRString *nameElement;

@property (nonatomic, strong) NSString *name;

/*
 * Name of the publisher (Organization or individual)
 */
@property (nonatomic, strong) FHIRString *publisherElement;

@property (nonatomic, strong) NSString *publisher;

/*
 * Contact information of the publisher
 */
@property (nonatomic, strong) NSArray/*<Contact>*/ *telecom;

/*
 * Natural language description of the profile
 */
@property (nonatomic, strong) FHIRString *descriptionElement;

@property (nonatomic, strong) NSString *description;

/*
 * Assist with indexing and finding
 */
@property (nonatomic, strong) NSArray/*<Coding>*/ *code;

/*
 * draft | active | retired
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kResourceProfileStatus status;

/*
 * If for testing purposes, not real usage
 */
@property (nonatomic, strong) FHIRBoolean *experimentalElement;

@property (nonatomic, strong) NSNumber *experimental;

/*
 * Date for this version of the profile
 */
@property (nonatomic, strong) FHIRDateTime *dateElement;

@property (nonatomic, strong) NSString *date;

/*
 * Scope and Usage this profile is for
 */
@property (nonatomic, strong) FHIRString *requirementsElement;

@property (nonatomic, strong) NSString *requirements;

/*
 * FHIR Version this profile targets
 */
@property (nonatomic, strong) FHIRId *fhirVersionElement;

@property (nonatomic, strong) NSString *fhirVersion;

/*
 * External specification that the content is mapped to
 */
@property (nonatomic, strong) NSArray/*<ProfileMappingComponent>*/ *mapping;

/*
 * A constraint on a resource or a data type
 */
@property (nonatomic, strong) NSArray/*<ProfileStructureComponent>*/ *structure;

/*
 * Definition of an extension
 */
@property (nonatomic, strong) NSArray/*<ProfileExtensionDefnComponent>*/ *extensionDefn;

- (FHIRErrorList *)validate;

@end
