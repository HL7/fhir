This profile sets minimum expectations for the [CareTeam] resource for identifying the Care team members associated with a patient. It identifies which core elements, extensions, vocabularies and value sets **SHALL** be present in the resource when using this profile.

**Example Usage Scenarios:**

The following are example usage scenarios for the DAF-CareTeam profile:

-   Query for a Patient's CareTeam
-   Query for all Patient' with a particular careteam member 


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each CareTeam must have:**

1.  a status code
1.  a patient
1.  a participant role for each careteam members
1.  names of careteam members which can be:
    -   a practitioner (doctor, nurse, therapist)
    -   the patient
    -   a relative or friend or guardian
    -   an organization


**Profile specific implementation guidance:**

* none

[CareTeam]:  http://hl7-fhir.github.io/careteam.html