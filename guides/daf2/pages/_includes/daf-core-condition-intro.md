This profile sets minimum expectations for use of the Condition resource to record, search and fetch a  list of problems and health concerns associated with a patient within the DAF FHIR IG. It identifies which core elements, extensions, vocabularies and value sets must be Supported by clients and servers. For the definition of Supported please refer to DAF FHIR IG. The data elements identified by the profile are based on ONC 2015 Edition Common Clinical Data Set(CCDS).


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each Condition must have:**

1.  a patient
1.  a code that identifies the problem
1.  a status of the problem
1.  a verification status


**Profile specific implementation guidance:**

* The DAF Condition Category Codes support the separate concepts of problems and health concerns so API consumers can separate health concerns and problems. However this is not mandatory for 2015 certification
* Additional elements from [DAF Condition Profile](daf-Condition.html) may be present.
