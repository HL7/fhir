#### U.S. Data Access Framework (DAF) Core AllergyIntolerance Profile


##### Scope and Usage

This profile sets minimum expectations for the [AllergyIntolerance] resource to record, search and fetch allergies/adverse events associated with a patient.  It identifies the mandatory core elements, extensions, vocabularies and value sets which **SHALL** be present in the AllergyIntolerance resource when using this profile.

**Example Usage Scenarios:**

The following are example usage scenarios for the DAF-Core AllergyIntolerance
profile:

-   Query for allergies belonging to a Patient
-   Query for all patients who have a specific allergy


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#summary) below provides the  formal summary, definitions, and  terminology requirements.  

**Each AllergyIntolerance must have:**

1.  a status of the allergy
2.  a code which tells you what the patient is allergic to 
3.  a patient

**Profile specific implementation guidance:**

* Representing No Known Allergies: No Known Allergies will be represented using the DAF-AllergyIntolerance profile with appropriate negation code in AllergyIntolerence.code.

####  Example Usage Scenarios

The following are example usage scenarios for the DAF-AllergyIntolerance
profile:

-   Query for allergies belonging to a Patient
-   Query for all patients who have had a specific allergy category
-   Query for allergies experienced during a time period
-   Query for allergies for a patient for a specific category
-   Query for allergies based on reaction and severity


[AllergyIntolerance]: http://hl7-fhir.github.io/allergyintolerance.html

