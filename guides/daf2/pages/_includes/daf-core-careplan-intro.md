This profile sets minimum expectations for the [CarePlan] resource to record search and fetch assessment and plan of treatment data associated with a patient. It identifies which core elements, extensions, vocabularies and value sets **SHALL** be present in the resource when using this profile.

**Example Usage Scenarios:**

The following are example usage scenarios for the DAF-CarePlan profile:

-   Query for a careplan for a Patient


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each CarePlan must have:**

1.  a narrative summary of the patient assessment and plan of treatment
1.  a patient
1.  a status
1.  a category code of “assess-plan”


**Profile specific implementation guidance:**

* none

[CarePlan]: http://hl7-fhir.github.io/careplan.html