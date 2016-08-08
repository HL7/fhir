When referring to medications, The [MedicationStatement] and [MedicationOrder] resources can either use a code or refer to a [Medication] resource.  This profile sets minimum expectations for the Medication resource to record search and fetch medications associated with a patient. It identifies which core elements, extensions, vocabularies and value sets **SHALL** be present in the resource when using this profile.

**Example Usage Scenarios:**

Queries on Medication resource are expected to be within the context of
a MedicationStatement,  MedicationOrder resource query. The following are
example usage scenarios for the DAF-Medication profile:

-   Query for Medications with a particular code

##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each Medication must have:**

1.  A medication code


**Profile specific implementation guidance:**
 
* none

[MedicationStatement]: http://hl7-fhir.github.io/medicationstatement.html
 [MedicationOrder]: http://hl7-fhir.github.io/medicationorder.html
 [Medication]: http://hl7-fhir.github.io/medication.html
