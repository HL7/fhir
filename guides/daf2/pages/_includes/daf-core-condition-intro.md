This profile sets minimum expectations for the [Condition] resource to record, search and fetch a  list of problems and health concerns associated with a patient. It identifies which core elements, extensions, vocabularies and value sets **SHALL** be present in the resource when using this profile.

**Example Usage Scenarios:**

The following are example usage scenarios for the DAF-Condition profile:

-   Query for a Patient’s problems
-   Query for all patients who have had a specific condition,problem or
    finding


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each Condition must have:**

1.  a status of the problem
1.  a verification status
1.  a code that identifies the problem
1.  a patient

**Profile specific implementation guidance:**

* The DAF Condition Category Codes support the separate concepts of problems and health concerns so API consumers can separate health concerns and problems. However this is not mandatory for 2015 certification

[Condition]: http://hl7-fhir.github.io/condition.html

