When referring to medications, The [MedicationStatement] and [MedicationOrder] resources can either use a code or refer to a Medication resource.  This profile sets minimum expectations for use of the Medication resource to record search and fetch medications associated with a patient within the DAF FHIR IG. It identifies which core elements, extensions, vocabularies and value sets must be Supported by clients and servers. For the definition of Supported please refer to DAF FHIR IG. The data elements identified by the profile are based on ONC 2015 Edition Common Clinical Data Set(CCDS).


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each Medication must have:**

1.  A medication code or a list of ingredients that comprise the medication


**Profile specific implementation guidance:**
 
* Additional elements from [DAF Medication Profile](daf-Medication.html) may be present.

[MedicationStatement]:daf-core-medicationstatement.html
 [MedicationOrder]: daf-core-medicationorder.html