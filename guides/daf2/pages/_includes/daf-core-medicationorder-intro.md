Both the [MedicationOrder] and [MedicationStatement] resources can be used to record a patient's medication.   For more information about the context for their usages, refer to the medication domains's [boundaries section].  This profile sets minimum expectations for the MedicationOrder resource to record, search and fetch medications associated with a patient. It identifies which core elements, extensions, vocabularies and value sets **SHALL** be present in the resource when using this profile.

**Example Usage Scenarios:**

The following are example usage scenarios for the DAF-MedicationOrder
profile:

-   Query for medications that have been prescribed to a particular
    patient
-   Query for all patients who have been prescribed a particular medication
-   Query for all patients who were prescribed a particular medication within a particular time period
    
##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each MedicationOrder must have:**

1.  a status
1.  a medication
1.  a patient
1.  a date for when written
1.  a prescriber



**Profile specific implementation guidance:**

*  The MedicationStatement and MedicationOrder resources can represent a medication, using either a code or reference to a [Medication] resource.  The server application can choose one way or both methods,  but the client application must support both methods.  More specific guidance is provided in the [conformance](conformance.html) resource for this profile


  [Medication Clinical Drug (RxNorm)]: valueset-daf-medication-codes.html
  [MedicationOrderStatus]: http://hl7.org/fhir/us/daf/valueset-medication-order-status.html
[MedicationStatementStatus]: http://hl7.org/fhir/us/daf/valueset-medication-statement-status.html
[MedicationStatement]:http://hl7-fhir.github.io/medicationstatement.html
 [MedicationOrder]: http://hl7-fhir.github.io/medicationorder.html
 [Medication]:http://hl7-fhir.github.io/medication.html
 [Conformance]: daf-core-medicationstatement-conformance.html
 [boundaries section]: http://hl7-fhir.github.io/medicationorder.html#bnr

 