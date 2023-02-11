There must be exactly 1 source patient, which may  be identified by either the source-patient or source-patient-identifier parameters.  Similarly, there must be exactly 1 target patient, identified by either the target-patient or target-patient-identifier parameters.  In both cases, either a reference to the patient or a list of identifiers that can be used to identify the patient may be provided, but not both.

The result-patient.id must be the same as the target patient reference (if the patient reference is provided as an input parameter)

If a client needs the server to create a new patient merged from the 2 patient resources, the client should create a new patient record and then call the merge operation to merge each old patient resource into the newly created patient resource.

A server may decide to delete the source record, but this is not defined by the standard merge operation, and if this occurs then the target patient's link property will remain unchanged.

# Merge Processing
The merge operation will have multiple stages, and some of these may take additional time for processing and thus be done asynchronously

|Stage | Description |
|-|-|
| Preview Merge | (Optional)<br/>This is a call to the operation (with preview=true) that simply checks for potential errors and warnings, without committing any changes.<br/>This might not be able to capture all possible causes of errors that could be encountered during the processing of the data patching.<br/>The returned Patient resource is a preview only and has not been committed. Hence the version number and last_modified date would be cleared/absent. |
| Initiate Merge | This stage processes the input parameters checking for errors/warnings and **prepares** to make changes to the patient resources.<br/>If the system is able to complete the processing of all reference data to the target patient, then **the merge process may complete immediately where no Task is needed and the data processing stage occurs as part of the Initiate Merge action**. Otherwise a Task for tracking would be created and monitor the progress of the merge. |
| Data Processing | The REST operation may have returned, and processing is ongoing to patch any other resource that references the source patient to reference the target patient.<br/>This may take a considerable period of time in some systems where the volume of records being updated is large.<br/>The source Patient record will be marked as inactive, and add the link property to the target patient (except where systems delete the record)
| Completed (or failed) | All data processing is complete, and the Task is marked as completed (maybe with errors) |

During the Data Processing stage any of the related patient resources (source, target, and result) and any resources referencing any of these patients may be indeterminate until the merge processing operation completes. These resources may be in the process of being changed or deleted, or having references updated, and there is no implied sequence for these updates to be made. There is also no implication that these changes are happening within a single transaction. Data consumers should wait until the merge process completes before querying for data about any of the relevant patients.

**Note:** Some servers may also update the inactive source patient resource to remove most of the data to make it more clear that the resource should not be used, and the replaced-by link is the key information. Even to the extent of clearing the name and contact details etc.

**Note:** During the pre-merge validation stage, a system may perform other internal checks/business rules.

## Merging Identifiers
If the result patient resource is provided in the parameters to the operation, then it is assumed that the caller has correctly included all the required identifiers desired to be in the target patient (though must include the identifiers specified in the input parameters).

If the result patient resource is **not** provided (only the identifier or reference to select it), then the values provided in the request parameters (source-patient.identifier and all source-patient-identifiers) will be copied into the target resource and marked as old.

> **Review Note:** If the marking of old still makes sense here for ALL provided identifiers that get copied across when the result patient isn't there)

The server may also migrate other identifiers (and properties) at its discretion, and choose to mark these as old or not.

**Note:** If an identifier value is masked, then the server will migrate the identifier value correctly, regardless of the masking.

## Updating Data that References the source Patient
The merge data processing SHALL update all references that refer to the source patient to reference the target patient.

While updating resources that reference the source patient, ensure that the target patient link value isn't also accidentally updated. (don't make it point at itself)

A Provenance resource SHOULD be created that references the source and target patient resources (or just the target-resource if the source was deleted, or the source patient's old identifier) indicating that the merge has occurred. (example below)

A Provenance resource MAY be created to link all of the resources that referenced the source patient that could then provide information to a potential un-merge operation.

**Note:** Some resources that have been updated as a result of the merge, such as AuditEvent, Provenance, may have been digitally signed, and this change would invalidate the signature. There may be other reasons impacting the updates that should be considered, further feedback on this specific use case is required.

> **Review Note:** Are there other implications of these reference updates that should be identified relating to versions - (such as where a version specific reference was included)

**Note:** While this processing is occurring, if a client requests clinical data for either source or target patient, an OperationOutcome with an informative message MAY be included in the resulting bundle indicating this processing is ongoing.

> **Review Note:** Considering updating http://hl7.org/fhir/valueset-issue-type.html to extended with a new child to the transient concept to indicate that merge processing is occurring.

## Post Merge Expectations
### Once the patient resources have been merged:
A GET on the source Patient resource ID (e.g. `GET [base]/Patient/pat01`) will return either:

* 200 OK and returns the source Patient which is now marked as inactive, and has the link (replaced-by) populated with the target Patient ID 
(Note: some systems may have cleared all the other properties making this a stub resource)
* 404 not found (when the merge system deleted the resource)

**Note:** Security implications such as those from SMART tokens could restrict access here.

When performing a SEARCH by the old Patient Resource ID return: e.g. `GET [base]/Patient?_id=pat01` (often used as a substitute for direct GET when doing _include for the managing org/general practitioner)

* 200 Ok Bundle with the inactive patient which is marked as inactive and has the link (replaced-by) populated in it (that you'll need to follow to get any further data)
* 200 Ok Bundle with no patient resource (case where the old patient was deleted)
* 200 Ok Bundle with the target patient resource (with the link to the one from the search) and not include the old patient resource
* 200 Ok Bundle with both the target and old patient resources

Accessing the Patient $everything operation on the source patient resource (now marked as inactive) will return an OperationOutcome and http status of 400 Bad Request. The error message should inform that the patient has been merged and should follow the Patient link to access the $everything content. 

> **Review Note:** Considering updating the http://hl7.org/fhir/valueset-issue-type.html to extended with a new child to the processing concept to indicate that additional content may be associated with a linked patient

Searching content (e.g. Observations) based on patient ID:

* `Observation?patient=Patient/pat01` would return a 200 Ok Bundle with no results (as all have been moved to Patient/pat02), an OperationOutcome may be included indicating that the patient was merged into patient xxx
* `Observation?patient=Patient/pat02` would return all the data that is referencing pat02, and all the data that was referencing pat01 (which was updated by the merge operation to reference pat02)

Use Case - Polling using search on Observations to get updates:

* `Observation?patient=Patient/pat02` (initial client call at start of year, gets all patient obs)
* `Observation?patient=Patient/pat02&_lastupdated=ge2019-03` (client calls at start of march to detect any new patient obs)
* (Patient pat01 is merged into pat02 during April)
* `Observation?patient=Patient/pat02&_lastupdated=ge2019-06` (client calls at start of june to detect any new patient obs, but misses all the pat01 observations prior to June)
* Client needs to check for a provenance record of the merge having taken place to determine that they need to refresh the local content to see the older data

In this case need to detect the patient merge, <u>and perform a fresh retrieval of all content</u>, there is no way for the server to return any error codes in this use case, and may also need to consider notification mechanisms too.

Creating/Updating content (e.g. Observations) that reference the old Patient ID: (feedback on this required)

* 422 Unprocessable Entity with an OperationOutcome indicating that the patient referenced was merged into patient xxx
(this is also the existing behavior if the patient resource was deleted)
* 201 Created if the service is able to automatically process the request and reallocate, this could occur during the merge data processing stage, otherwise the above code should be returned

# Merge Notification Mechanisms
The indication that a merge has been completed can be notified through several ways:

* Using FHIR Messaging to invoke the same operation
* An integration engine sending HL7v2 A40 merge message (A18 may also be applicable in backward compatibility modes)
* Directly calling the $merge operation on the dependent systems (requires the system to have both patient resources)
* Client data refresh notification (to be defined, could be triggered by merge, security changes, system migrations, consent changes, etc...)
* Using Subscriptions to detect the merge operation has occurred (on Provenance and/or Patient)
* Polling the Merge Provenance resource (or the Patient resource for the relevant link change)
* (other non standard notification channels)

These notifications can be sent to other downstream systems, partners, or other applications (including EMPIs). An EMPI could expose the merge operation, and therefore be a notification sender.

Consideration should be taken to ensure that the correct data is acted on.

The downstream systems might not have all identifiers that the notifying system has, the notifier may be configured to know what "types" of identifiers should be propagated to which systems.

**Note:** When using the identifier parameters (rather than id) you should be using the same assigner (which in the example above would be the PAS/ADT or clinical system), this may be configured in the sending notification system, such as an EMPI based on local business rules.

# Impact on Subscriptions
Subscriptions on merges are most likely to be used by applications connecting directly to the system. Many use cases could consider using FHIR Messaging (or other messaging e.g. v2 messages) to communicate the merge occurred.

> **Review Note:** Interaction with the Subscription v2 resources requires additional review and implementer feedback considering:
> * What can be used as triggers for the subscription
>    * Patient update with new link values
>    * Provenance(s) as an event
>    * operation itself as an event (the Task resource, although that might not exist, so just a pre-defined topic)
> * Will all the data that is patched over to the target patient ID be notified
>    * Systems might not notify that the content was changed, and rely on the merge notification to advise if required
>    * Also note the Client data refresh notification discussion above

# Mapping HL7v2 Merge to FHIR
## HL7v2 Merges, Move and Linking
Below is a summary of the Merge, Move and Link operations. They are included here as the v2 concepts of Merge, Move and Link may differ (or not) based on the establishment of the Patient, Encounter and Account as separate FHIR resources. The Merge, Move and Link operations  have 3 levels: Patient Identifier, Patient Account, and Patient Visit. 

## Definitions:  Merge, move, and change identifier events
The term "identifier" is used throughout this section.  An identifier is associated with a set (or sets) of data.  For example, an identifier (PID-3 - Patient Identifier List) may be a medical record number which has associated with it account numbers (PID-18 - Patient Account Number).  Account number (PID-18 - Patient Account Number) is a type of identifier which may have associated with it visit numbers (PV1-19 - Visit Number).

This section addresses the events that occur usually for the purposes of correcting errors in person, patient, account, or visit identifiers.  The types of errors that occur typically fall into three categories:
* Duplicate identifier created  
The registrar fails to identify an existing person, patient, account, or visit and creates a new, "duplicate" record instead of using the existing record. A "merge" operation is used to fix this type of error.
* Incorrect identifier selected  
The registrar mistakenly selects the wrong person, patient, or account and creates or attaches a patient, account, or visit underneath the incorrect person, patient, or account. A "move" operation is used to fix this type of error.
* Incorrect identifier assigned  
The registrar accidentally types in the wrong new identifier for a person, patient, account, or visit. This type of mistake usually occurs when identifiers are manually assigned (not system generated).  A "change identifier" operation is used to fix this type of error.

**Note:** HL7v2 addresses only scenarios 1 and 2 as most identifiers are assigned by the related systems, today.

## Patient record links

Linking two or more patients does not require the actual merging of patient information; following a link trigger event, sets of affected patient data records should remain distinct.  However, because of differences in database architectures, there may be system-dependent limitations or restrictions regarding the linking of one or more patients that must be negotiated.

There are multiple approaches for implementing MPIs.  It is useful for the purpose of MPI mediation to support two types of linkage.  Explicit linkage requires a message declaring a link has been made between multiple identifiers.  Implicit linkage is performed when a receiving system infers the linkage from the presence of multiple identifiers present in *PID-3-patient identifier list*.

In an MPI setting, the A24 -link patient information message is preferred for transmitting an explicit link of identifiers whether they are in the same or different assigning authorities.  The A37 unlink patient information message is preferred for transmitting the explicit unlinking of identifiers.

Implicit linkage of identifiers, sometimes called passive linking, has been implemented using various messages.  An acknowledged method is inclusion of multiple identifiers in *PID-3-patient identifier list*, which the receiving system implicitly links.  An MPI or application that makes such an implicit linkage can generate an A24 - link patient information message to explicitly notify another system of this action.

## Merge Events

### ADT/ACK - Merge Patient - Patient Identifier List (Event A40)
***(This is the primary event to be considered associated with FHIR Patient merge)***

A merge has been done at the patient identifier list level.  That is, two PID-3 - Patient Identifier List identifiers have been merged into one.

An A40 event is used to signal a merge of records for a patient that was incorrectly filed under two different identifiers.  The "incorrect source identifier" identified in the MRG segment (MRG-1 - Prior Patient Identifier List) is to be merged with the required "correct target identifier" of the same "identifier type code" component identified in the PID segment (PID-3 - Patient Identifier List). The "incorrect source identifier" would then logically never be referenced in future transactions.  It is noted that some systems may still physically keep this "incorrect identifier" for audit trail purposes or other reasons associated with database index implementation requirements.

### ADT/ACK - Merge Account - Patient Account Number (Event A41)
*(For information only, not in scope of FHIR for now)*

A merge has been done at the account identifier level.  That is, two PID-18 - Patient Account Number identifiers have been merged into one.

An A41 event is used to signal a merge of records for an account that was incorrectly filed under two different account numbers.  The "incorrect source patient account number" identified in the MRG segment (MRG-3 - Prior Patient Account Number) is to be merged with the "correct target patient account number" identified in the PID segment (PID-18 - Patient Account Number).  The "incorrect source patient account number" would then logically never be referenced in future transactions.  It is noted that some systems may still physically keep this "incorrect identifier" for audit trail purposes or other reasons associated with database index implementation requirements.

> **Implementer Note:** This is not merging the Patient, but merging the account, but is the same concept, should we also be including this concept as another potential operation?

### ADT/ACK - Merge Visit - Visit Number (Event A42)
*(For information only, not in scope of FHIR for now)*

A merge has been done at the visit identifier level.  That is, two PV1-19 - Visit Number identifiers have been merged into one.

An A42 event is used to signal a merge of records for a visit that was incorrectly filed under two different visit numbers.  The "incorrect source visit number" identified in the MRG segment (MRG-5 - Prior Visit Number) is to be merged with the required "correct target visit number" identified in the PV1 segment (PV1-19 - Visit Number).  The "incorrect source visit number" would then logically never be referenced in future transactions.  It is noted that some systems may still physically keep this "incorrect identifier" for audit trail purposes or other reasons associated with database index implementation requirements.

> **Implementer Note:** Would be interesting to determine if these are used in production (A41 and A42)

## Move Events
### ADT/ACK - Move Patient Information - Patient Identifier List (Event A43)

A move has been done at the patient identifier list level.  Identifier to be moved in the PID-3 - Patient Identifier List and MRG-1 - Prior Patient Identifier List will have the same value. The "from" (incorrect source patient ID) and "to" (correct target patient ID) identifiers have different values. See A43 examples in section 5.  The identifiers involved in identifying the patient to be moved (MRG-1 - Prior Patient Identifier List) may or might not have accounts, which may or might not have visits.  In any case, all subordinate data sets associated with the identifier in MRG-1 - Prior Patient Identifier List are moved along with the identifier, from the "incorrect source patient ID" to the "correct target patient ID."

### ADT/ACK - Move Account Information - Patient Account Number (Event A44)
*(For information only, not in scope of FHIR for now)*

A move has been done at the account identifier level.  That is, a PID-18 - Patient Account Number associated with one PID-3 - Patient Identifier List has been moved to another patient identifier list.

An A44 event is used to signal a move of records identified by the MRG-3 - Prior Patient Account Number from the "incorrect source patient identifier list" identified in the MRG segment (MRG-1 - Prior Patient Identifier List) to the "correct target patient identifier list" identified in the PID segment (PID-3 - Patient Identifier List).

### ADT/ACK - Move Visit Information - Visit Number (Event A45)
*(For information only, not in scope of FHIR for now)*

A move has been done at the visit identifier level.  That is, a PV1-19 - Visit Number or PV1-50 - Alternate Visit ID associated with one account identifier (PID-18 - Patient Account Number) has been moved to another account identifier.

An A45 event is used to signal a move of records identified by the MRG-5 - Prior Visit Number or the MRG-6 - Prior Alternate Visit ID from the "incorrect source account identifier" identified in the MRG segment (MRG-3 - Prior Patient Account Number) to the "correct target account identifier" identified in the PID segment (PID-18 - Patient Account Number).

> **Review Note:** Should Event A47 be covered? (this is similar to A43), Some vendor(s?) implement this and not A43

## Link Events
### ADT/ACK - link patient information (event A24)
*(For information only, not in scope of FHIR for now)*

The A24 event is used when the first PID segment needs to be linked to the second PID segment and when both patient identifiers identify the same patient.  Linking two or more patients does not require the actual merging of patient information; following a link event, the affected patient data records should remain distinct.  For example, this event could be used in a hospital network environment in which there are multiple campuses and in which records need to be linked.  For example, hospital A, hospital B, and hospital C would each keep their own records on a patient, but an A24 link event would be sent to a corporate-wide MPI to enable the coupling of ID information with the corporate ID number.  It is used for corporate data repositories, etc.  This event is not meant to link mothers and babies since a field exists (PID-21-mother’s identifier) for that purpose.  See Section 3.5.3, “Patient record links,” for a discussion of issues related to implementing patient link messages and MPI issues.

This event can also be used to link two patient identifiers when a patient changes from inpatient to outpatient, or vice versa.  This event can also be used to link two visits of the same patient.

The fields included when this message is sent should be the fields pertinent to communicate this event.  When other important fields change, it is recommended that the A08 (update patient information) event be used in addition.

### ADT/ACK - unlink patient information (event A37)
*(For information only, not in scope of FHIR for now)*

The A37 event unlinks two PID segments previously linked with an A24 (link patient information) event.

# Safety Checklist
> **Review Note:** Seeking implementer feedback on safety checklist items to include
