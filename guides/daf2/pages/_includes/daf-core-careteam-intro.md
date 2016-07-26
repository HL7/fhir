This profile sets minimum expectations for use of the CareTeam resource for identifying the Care team members associated with a patient within the DAF FHIR IG. It identifies which core elements, extensions, vocabularies and value sets must be Supported by clients and servers. For the definition of Supported please refer to DAF FHIR IG. The data elements identified by the profile are based on ONC 2015 Edition Common Clinical Data Set(CCDS).


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each CareTeam must have:**

1.  a patient
1.  names of careteam members which can be:
    -   a practitioner (doctor, nurse, therapist)
    -   the patient
    -   a relative or friend or guardian
    -   an organization
1.  a participant role for each careteam members
1.  a status code of “active”

**Profile specific implementation guidance:**

* none
