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
 * Event record kept for security purposes
 *
 * [FhirResource("SecurityEvent")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRSecurityEventEventComponent;
@class FHIRSecurityEventParticipantComponent;
@class FHIRSecurityEventSourceComponent;
@class FHIRSecurityEventObjectComponent;

@interface FHIRSecurityEvent : FHIRResource

/*
 * Indicator for type of action performed during the event that generated the audit.
 */
typedef enum 
{
    kSecurityEventActionC, // Create a new database object, such as Placing an Order.
    kSecurityEventActionR, // Display or print data, such as a Doctor Census.
    kSecurityEventActionU, // Update data, such as Revise Patient Information.
    kSecurityEventActionD, // Delete items, such as a doctor master file record.
    kSecurityEventActionE, // Perform a system or application function such as log-on, program execution or use of an object's method, or perform a query/search operation.
} kSecurityEventAction;

/*
 * The type of network access point that originated the audit event
 */
typedef enum 
{
    kSecurityEventParticipantNetworkTypeN1, // Machine Name, including DNS name.
    kSecurityEventParticipantNetworkTypeN2, // IP Address.
    kSecurityEventParticipantNetworkTypeN3, // Telephone Number.
    kSecurityEventParticipantNetworkTypeN4, // Email address.
    kSecurityEventParticipantNetworkTypeN5, // URI (User directory, HTTP-PUT, ftp, etc.).
} kSecurityEventParticipantNetworkType;

/*
 * Code representing the functional application role of Participant Object being audited
 */
typedef enum 
{
    kSecurityEventObjectRoleN1, // This object is the patient that is the subject of care related to this event.  It is identifiable by patient ID or equivalent.  The patient may be either human or animal.
    kSecurityEventObjectRoleN2, // This is a location identified as related to the event.  This is usually the location where the event took place.  Note that for shipping, the usual events are arrival at a location or departure from a location.
    kSecurityEventObjectRoleN3, // This object is any kind of persistent document created as a result of the event.  This could be a paper report, film, electronic report, DICOM Study, etc.  Issues related to medical records life cycle management are conveyed elsewhere.
    kSecurityEventObjectRoleN4, // A logical object related to the event.  (Deprecated).
    kSecurityEventObjectRoleN5, // This is any configurable file used to control creation of documents.  Examples include the objects maintained by the HL7 Master File transactions, Value Sets, etc.
    kSecurityEventObjectRoleN6, // A human participant not otherwise identified by some other category.
    kSecurityEventObjectRoleN7, // (deprecated).
    kSecurityEventObjectRoleN8, // Typically a licensed person who is providing or performing care related to the event, generally a physician.   The key distinction between doctor and practitioner is with regards to their role, not the licensing.  The doctor is the human who actually performed the work.  The practitioner is the human or organization that is responsible for the work.
    kSecurityEventObjectRoleN9, // A person or system that is being notified as part of the event.  This is relevant in situations where automated systems provide notifications to other parties when an event took place.
    kSecurityEventObjectRoleN10, // Insurance company, or any other organization who accepts responsibility for paying for the healthcare event.
    kSecurityEventObjectRoleN11, // A person or active system object involved in the event with a security role.
    kSecurityEventObjectRoleN12, // A person or system object involved in the event with the authority to modify security roles of other objects.
    kSecurityEventObjectRoleN13, // A passive object, such as a role table, that is relevant to the event.
    kSecurityEventObjectRoleN14, // (deprecated)  Relevant to certain RBAC security methodologies.
    kSecurityEventObjectRoleN15, // Any person or organization responsible for providing care.  This encompasses all forms of care, licensed or otherwise, and all sorts of teams and care groups. Note, the distinction between practitioners and the doctor that actually provided the care to the patient.
    kSecurityEventObjectRoleN16, // The source or destination for data transfer, when it does not match some other role.
    kSecurityEventObjectRoleN17, // A source or destination for data transfer, that acts as an archive, database, or similar role.
    kSecurityEventObjectRoleN18, // An object that holds schedule information.  This could be an appointment book, availability information, etc.
    kSecurityEventObjectRoleN19, // An organization or person that is the recipient of services.  This could be an organization that is buying services for a patient, or a person that is buying services for an animal.
    kSecurityEventObjectRoleN20, // An order, task, work item, procedure step, or other description of work to be performed.  E.g., a particular instance of an MPPS.
    kSecurityEventObjectRoleN21, // A list of jobs or a system that provides lists of jobs.  E.g., an MWL SCP.
    kSecurityEventObjectRoleN22, // (Deprecated).
    kSecurityEventObjectRoleN23, // An object that specifies or controls the routing or delivery of items.  For example, a distribution list is the routing criteria for mail.  The items delivered may be documents, jobs, or other objects.
    kSecurityEventObjectRoleN24, // The contents of a query.  This is used to capture the contents of any kind of query.  For security surveillance purposes knowing the queries being made is very important.
} kSecurityEventObjectRole;

/*
 * Code for the participant object type being audited
 */
typedef enum 
{
    kSecurityEventObjectTypeN1, // Person.
    kSecurityEventObjectTypeN2, // System Object.
    kSecurityEventObjectTypeN3, // Organization.
    kSecurityEventObjectTypeN4, // Other.
} kSecurityEventObjectType;

/*
 * Identifier for the data life-cycle stage for the participant object
 */
typedef enum 
{
    kSecurityEventObjectLifecycleN1, // Origination / Creation.
    kSecurityEventObjectLifecycleN2, // Import / Copy from original.
    kSecurityEventObjectLifecycleN3, // Amendment.
    kSecurityEventObjectLifecycleN4, // Verification.
    kSecurityEventObjectLifecycleN5, // Translation.
    kSecurityEventObjectLifecycleN6, // Access / Use.
    kSecurityEventObjectLifecycleN7, // De-identification.
    kSecurityEventObjectLifecycleN8, // Aggregation, summarization, derivation.
    kSecurityEventObjectLifecycleN9, // Report.
    kSecurityEventObjectLifecycleN10, // Export / Copy to target.
    kSecurityEventObjectLifecycleN11, // Disclosure.
    kSecurityEventObjectLifecycleN12, // Receipt of disclosure.
    kSecurityEventObjectLifecycleN13, // Archiving.
    kSecurityEventObjectLifecycleN14, // Logical deletion.
    kSecurityEventObjectLifecycleN15, // Permanent erasure / Physical destruction.
} kSecurityEventObjectLifecycle;

/*
 * Indicates whether the event succeeded or failed
 */
typedef enum 
{
    kSecurityEventOutcomeN0, // The operation completed successfully (whether with warnings or not).
    kSecurityEventOutcomeN4, // The action was not successful due to some kind of catered for error (often equivalent to an HTTP 400 response).
    kSecurityEventOutcomeN8, // The action was not successful due to some kind of unexpected error (often equivalent to an HTTP 500 response).
    kSecurityEventOutcomeN12, // An error of such magnitude occurred that the system is not longer available for use (i.e. the system died).
} kSecurityEventOutcome;

/*
 * What was done
 */
@property (nonatomic, strong) FHIRSecurityEventEventComponent *event_;

/*
 * A person, a hardware device or software process
 */
@property (nonatomic, strong) NSArray/*<SecurityEventParticipantComponent>*/ *participant;

/*
 * Application systems and processes
 */
@property (nonatomic, strong) FHIRSecurityEventSourceComponent *source;

/*
 * Specific instances of data or objects that have been accessed
 */
@property (nonatomic, strong) NSArray/*<SecurityEventObjectComponent>*/ *object_;

- (FHIRErrorList *)validate;

@end
