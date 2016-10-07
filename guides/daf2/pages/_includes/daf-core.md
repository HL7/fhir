## Introduction

The DAF-Core implementation guide defines the minimum mandatory requirements for recording, searching for, and fetching patient information.  The data elements, extension, and terminology used in the DAF-Core IG are based on [ONC 2015 Edition Common Clinical Data Set (CCDS)] in addition to essential administrative and conformance requirements.  
The profiles described in this IG are used by [DAF-Core], [DAF-Research] IGs and are intended to be a common core for other US Realm guides.

## DAF-Core Actors 

The following actors are part of the DAF-Core IG.

* DAF Requestor: An application that initiates a data access request to retrieve patient data. This can be thought of as the client in a client-server interaction.
* DAF Responder: A product that responds to the data access request providing patient data. This can be thought of as the server in a client-server interaction.

## Definitions, Interpretations and Requirements common to all DAF actors 

This section outlines important definitions and interpretations used in the DAF-Core IG.
The conformance verbs used are defined in [FHIR Conformance Rules].

In the context of DAF, Supported on any data element SHALL be interpreted as follows:

* DAF Responders SHALL be capable of including the data element as part of the query results as specified by the DAF conformance resources.
* DAF Requestors SHALL be capable of processing resource instances containing the data elements. In other words DAF Requestors SHOULD be capable of displaying the data elements for human use or storing it for other purposes.
* In situations where information on a particular data element is not present and the reason for absence is unknown, DAF Responders SHALL NOT include the data elements in the resource instance returned as part of the query results.
* When querying DAF Responders, DAF Requestors SHALL interpret missing data elements within resource instances as data not present in the DAF Responder's systems.
* In situations where information on a particular data element is missing and the DAF Responder knows the precise reason for the absence of data, DAF Responders SHALL send the reason for the missing information using values from the value set where they exist or using the dataAbsentReason extension.
* DAF Requestors SHALL be able to process resource instances containing data elements asserting missing information.

* NOTE: DAF Responders who do not have the capability to store or return a data element tagged as Supported in DAF-Core profiles can still claim conformance to the DAF-Core profiles per the DAF-Core conformance resources.
* NOTE: The above definition of Supported is derived from HL7v2 concept "Required by may be empty - RE" described in HL7v2 V28_CH02B_Conformance.doc.
* NOTE: Readers are advised to understand [FHIR Terminology] requirements, [FHIR RESTful API] based on the [HTTP] protocol, along with [FHIR Data Types], [FHIR Search] and [FHIR Resource] formats before implementing DAF requirements.


## DAF Core Profiles

The following table lists the [profiles] and the base FHIR Resources from which they were derived. Each profile defines the minimum mandatory elements, extensions and terminology requirements that **MUST** be present. For each profile requirements and guidance are given in a simple narrative summary. A formal hierarchical table that presents a [logical view] of the content in both a differential and snapshot view is also provided along with references to appropriate terminologies and examples.

|DAF-Core Profile|Base Resource|
|-------------- |---------------|
|[AllergyIntolerance](daf-core-allergyintolerance.html)|AllergyIntolerance|
|[Assessment and Plan of Treatment](daf-core-careplan.html)|CarePlan|
|[CareTeam](daf-core-careteam.html)|CareTeam|
|[Condition](daf-core-condition.html)|Condition|
|[Device-UDI](daf-core-device.html)|Device|
|[Goal](daf-core-goal.html)|Goal|
|[Immunization](daf-core-immunization.html)|Immunization|
|[Lab DiagnosticReport](daf-core-diagnosticreport.html)|DiagnosticReport|
|[Lab Observation](daf-core-resultobs.html)|Observation|
|[Location](daf-core-location.html)|Location|
|[Medication](daf-core-medication.html)|Medication|
|[MedicationOrder](daf-core-medicationorder.html)|MedicationOrder|
|[MedicationStatement](daf-core-medicationstatement.html)|MedicationStatement|
|[Observation-Smokingstatus](daf-core-smokingstatus.html)|Observation|
|[Observation-Vitalsigns](daf-core-vitalsigns.html)|Observation|
|[Organization](daf-core-organization.html)|Organization|
|[Patient](daf-core-patient.html)|Patient|
|[Practitioner](daf-core-pract.html)|Practitioner|
|[Procedure](daf-core-procedure.html)|Procedure|

## DAF-Core Conformance Requirements

This section outlines conformance requirements for each of the DAF actors identifying the specific profiles that need to be supported, the specific RESTful operations that need to be supported, and the search parameters that need to be supported. Note: The individual DAF-core profiles identify the structural constraints, terminology bindings and invariants, however, implementers must refer to the conformance requirements for details on the RESTful operations, specific profiles and the search parameters applicable to each of the DAF actors.

* [Conformance requirements for the DAF Requestor actor] which is responsible for creating and initiating the queries for information about an individual patient.
* [Conformance requirements for the DAF Responder actor] which is responsible for providing responses to the queries submitted by the DAF Requestors.

## DAF-Core Security Requirements

DAF actors must implement applicable security requirements identified in the [DAF-Core Security] section.



[DAF]: daf.html
[DAF-Core]: daf-core.html
[DAF-Research]: daf-research.html
[Conformance requirements for the DAF Requestor actor]: capabilitystatement-daf-query-requestor.html 
[Conformance requirements for the DAF Responder actor]: capabilitystatement-daf-query-responder.html
[DAF-Core Security]: daf-security.html
[ONC]: http://www.healthit.gov/newsroom/about-onc 
[Data Access Framework (DAF)]: http://wiki.siframework.org/Data+Access+Framework+Homepage
[PCORnet]: http://www.pcornet.org/
[Argonaut]: http://argonautwiki.hl7.org/index.php?title=Main_Page
[ONC 2015 Edition Common Clinical Data Set (CCDS)]: https://www.healthit.gov/sites/default/files/2015Ed_CCG_CCDS.pdf
[profiles]: http://hl7-fhir.github.io/profiling.html
[logical view]: http://hl7-fhir.github.io/formats.html#table
[StructureDefinitions]: http://hl7-fhir.github.io/structuredefinition.html
[Value sets]: http://hl7-fhir.github.io/valueset.html
[CodeSystem]: http://hl7-fhir.github.io/codesystem.html
[ConceptMap]: http://hl7-fhir.github.io/conceptmap.html
[NamingSystem]: http://hl7-fhir.github.io/namingsystem.html
[FHIR Conformance Rules]: http://hl7-fhir.github.io/capabilitystatement-rules.html
[dataAbsentReason]: http://hl7-fhir.github.io/extension-data-absent-reason.html
[FHIR Terminology]: http://hl7-fhir.github.io/terminologies.html
[FHIR RESTful API]: http://hl7-fhir.github.io/http.html
[HTTP]: http://hl7-fhir.github.io/http.html
[FHIR Data Types]: http://hl7-fhir.github.io/datatypes.html
[FHIR Search]: http://hl7-fhir.github.io/search.html
[FHIR Resource]: http://hl7-fhir.github.io/formats.html




