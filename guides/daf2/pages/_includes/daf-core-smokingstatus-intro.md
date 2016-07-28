
This profile sets minimum expectations for use of the Observation resource to record, search and fetch smoking status data associated with a patient within the DAF FHIR IG. It identifies which core elements, extensions, vocabularies and value sets must be Supported by clients and servers. For the definition of Supported please refer to DAF FHIR IG. The data elements identified by the profile are based on ONC 2015 Edition Common Clinical Data Set(CCDS).


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each Observation must have:**

1.  a patient
1.  a smoking status code
1.  a result value code for smoking status
1.  a date representing when the smoking status was recorded
1.  a status

**Profile specific implementation guidance:**

* Additional elements from [DAF Observation -Smoking StatusProfile](observation-daf-smokingstatus.html) may be present.
