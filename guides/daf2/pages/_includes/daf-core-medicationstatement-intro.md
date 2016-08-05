Both the [MedicationOrder] and [MedicationStatement] resources can be used to record a patient's medication.  For more information about the context for their usages, refer to the medication domains's [boundaries section].  This profile sets minimum expectations for the MedicationStatement resource to record, search and fetch medications associated with a patient. It identifies which core elements, extensions, vocabularies and value sets **SHALL** be present in the resource when using this profile.


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each MedicationStatement must have:**

1.  a patient
1.  a status
1.  a date or date range
1.  a medication


**Profile specific implementation guidance:**

*  The MedicationStatement and MedicationOrder resources can represent a medication, using either a code or refer to a [Medication] resource.  The server application can choose one way or both methods,  but the client application must support both methods.  More specific guidance is provided in the [Conformance] resource for this profile


  [Medication Clinical Drug (RxNorm)]: valueset-daf-medication-codes.html
  [MedicationOrderStatus]: http://hl7.org/fhir/us/daf/valueset-medication-order-status.html
[MedicationStatementStatus]: http://hl7.org/fhir/us/daf/valueset-medication-statement-status.html
[MedicationStatement]:http://hl7-fhir.github.io/medicationstatement.html
 [MedicationOrder]: http://hl7-fhir.github.io/medicationorder.html
 [Medication]:http://hl7-fhir.github.io/medication.html
 [Conformance]: daf-core-medicationstatement-conformance.html
 [boundaries section]: http://hl7-fhir.github.io/medicationorder.html#bnr

 