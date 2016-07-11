# U.S. Data Access Framework (DAF) AllergyIntolerance Core Profile 

 
## Scope and Usage 

This profile sets minimum expectations for use of the AllergyIntolerance resource to record allergies/adverse events associated with a patient within the DAF FHIR IG. It identifies which core elements, extensions, vocabularies and value sets must be Supported by clients and servers. For the definition of Supported please refer to DAF FHIR IG. The data elements identified by the profile are based on ONC 2015 Edition Common Clinical Data Set(CCDS).

 
Mandatory Data Elements
-----------------------

The following data-elements are mandatory (i.e data MUST be present). These are presented below in simple human-readable explanation as well as profile specific implementation guidance.  The [**Formal Profile Definition**](daf-core-allergyintolerance.html) provides a formal summary and machine processable structuredefinition of the requirements, constraints and extensions of the AllergyIntolerance resource.  An example is provided below to demonstrate the requirement.

**Each AllergyIntolerance must have:**

1.  a patient
2.  a code which tells you what the patient is allergic to
3.  a status of the allergy

**Profile specific implementation guidance:**

* Representing No Known Allergies: No Known Allergies will be represented using the DAF-AllergyIntolerance profile with appropriate negation code in AllergyIntolerence.substance.
* Additional elements from [DAF AllergyIntolerance Profile](daf-allergyintolerance.html) may be present.



 

### [Formal Profile Definition](daf-core-allergyintolerance.html)

**Resource Example:**

* [AI-Example1]()

 
## Search Parameters 

The following search required search and read operations are required to conform to this profile:

`GET /AllergyIntolerance?patient=[id]`

Support: Mandatory to support search by patient.

Implementation Notes: Search for all allergies for a patient. Fetches a bundle of all AllergyIntolerance resources for the specified patient (how to search by reference).

Response Class:

* (Status 200): successful operation

* (Status 400): invalid parameter

* (Status 401/4xx): unauthorized request

* (Status 403): insufficient scope

Example:

* [GET https://fhir-open-api-dstu2.smarthealthit.org/AllergyIntolerance?patient=1137192](https://fhir-open-api-dstu2.smarthealthit.org/AllergyIntolerance?patient=1137192)

