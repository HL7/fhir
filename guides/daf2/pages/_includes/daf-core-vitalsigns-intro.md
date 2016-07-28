This profile sets minimum expectations for use of the Observation resource to record, search and fetch vital signs associated with a patient within the DAF FHIR IG. It identifies which core elements, extensions, vocabularies and value sets must be Supported by clients and servers. For the definition of Supported please refer to DAF FHIR IG. The data elements identified by the profile are based on ONC 2015 Edition Common Clinical Data Set(CCDS).


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each Observation must have:**

-   a patient
-   a time indicating when the measurement was taken
-   a LOINC code which tells you what is being measured and is taken from the “LOINC Code” column in the table below.
    -   note: If a more specific code is recorded, the generic code and the translated coded must be sent - e.g. method specific LOINC Codes, SNOMED CT concepts, system specific codes
-   a numeric result value and standard UCUM unit which is taken from the “LOINC Code” column in the table below.
    -   note: if there is no numeric result then you have to supply a reason
-   a status
-   a category code of 'vital-signs'. 

**Profile specific implementation guidance:**

* This table represents a minimum set of vital sign concepts, the required LOINC codes ([Argonaut Vital Signs]), and UCUM units of measure codes ([Argonaut Vital Signs Units]) used for representing vitals signs observations. These are [extensible bindings] defined for this IG and require that when a system support of any of these vital signs concepts, they must represent them using these codes. In addition, if you have a blood pressure observation, you must have both a systolic and a diastolic component, though one or both may have dataAbsentReason instead of a value.

* Alternate codes may be provided in addition to the standard LOINC and UCUM codes defined here. An example of [using multiple codes] is provided. Other profiles may make rules about which vital sign must be present or must be present as part of a panel.

* Additional elements from [DAF Observation Profile](daf-Observation.html) may be present.
