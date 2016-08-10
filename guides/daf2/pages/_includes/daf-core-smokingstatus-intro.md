


This profile sets minimum expectations for the [Observation] resource to record, search and fetch smoking status data associated with a patient. It identifies which core elements, extensions, vocabularies and value sets **SHALL** be present in the resource when using this profile.

**Example Usage Scenarios:**

The following are example usage scenarios for the DAF-SmokingStatus
profile:

-   Query for Smoking Status of a particular patient
-   Query for all patients who have had a history of smoking
-   Query for all patients who have never had tobacco
-   Query for all patients who have smoked during a time period of
    interest
-   Query for all patients who have a smoking history and are in an age
    group

##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each Observation must have:**

1.  a status
1.  a fixed code for smoking observation
1.  a patient
1.  a date representing when the smoking status was recorded
1.  a result value code for smoking status


**Profile specific implementation guidance:**

* none

[Observation]: http://hl7-fhir.github.io/observation.html
