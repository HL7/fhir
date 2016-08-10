## Introduction

DAF-Core IG focuses on defining the minimum mandatory requirements for recording, searching for and fetching patient information.  
The data elements, extension and terminology identified by the various profiles in the DAF-Core IG are based on [ONC 2015 Edition Common Clinical Data Set (CCDS)] as well as essential administrative and conformance requirements.  
The profiles described in this IG are used by [DAF-Core], [DAF-Research] IGs and are intended to be leveraged as a common core by other US Realm implementations as well. 

## DAF-Core Actors 

The following actors are part of the DAF-Core IG.

* DAF Requester: A Health IT system that initiates the data access to retrieve patient data. This can be thought of as the client in a client-server interaction.
* DAF Responder: A Health IT system that responds to the data access request providing patient data. This can be thought of as the server in a client-server interaction.

## Definitions, Interpretations and Requirements common to all DAF actors 

This section outlines important definitions and interpretations used in the DAF-Core IG.
The conformance verbs used are defined in [FHIR Conformance Rules].

In the context of DAF, Supported on any data element SHALL be interpreted as follows:

* DAF Responders SHALL be capable of including the data element as part of the query results as specified by the DAF conformance resources.
* DAF Requesters SHALL be capable of processing resource instances containing the data elements. In other words DAF Requesters SHOULD be capable of displaying the data elements for human use or storing it for other purposes.
* In situations where information on a particular data element is not present and the reason for absence is unknown, DAF Responders SHALL NOT include the data elements in the resource instance returned as part of the query results.
* When querying DAF Responders, DAF Requesters SHALL interpret missing data elements within resource instances as data not present in the DAF Responder's systems.
* In situations where information on a particular data element is missing and the DAF Responder knows the precise reason for the absence of data, DAF Responders SHALL send the reason for the missing information using values from the value set where they exist or using the dataAbsentReason extension.
* DAF Requesters SHALL be able to process resource instances containing data elements asserting missing information.

* NOTE: DAF Responders who do not have the capability to store or return a data element tagged as Supported in DAF-Core profiles can still claim conformance to the DAF-Core profiles per the DAF-Core conformance resources.
* NOTE: The above definition of Supported is derived from HL7v2 concept "Required by may be empty - RE" described in HL7v2 V28_CH02B_Conformance.doc.
* NOTE: Readers are advised to understand [FHIR Terminology] requirements, [FHIR RESTful API] based on the [HTTP] protocol, along with [FHIR Data Types], [FHIR Search] and [FHIR Resource] formats before implementing DAF requirements.

## DAF-Core Profiles

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




## DAF-Core Security Requirements



[DAF]: daf.html
[DAF-Core]: daf-core.html
[DAF-Research]: daf-research.html
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
[FHIR Conformance Rules]: conformance-rules.html
[dataAbsentReason]: extension-data-absent-reason.html
[FHIR Terminology]: terminologies.html
[FHIR RESTful API]: http.html
[HTTP]: http.html
[FHIR Data Types]: datatypes.html
[FHIR Search]: search.html
[FHIR Resource]: formats.html




